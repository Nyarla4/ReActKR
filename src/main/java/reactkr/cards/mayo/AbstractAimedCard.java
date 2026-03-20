package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.Mayo;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.powers.mayo.*;
import reactkr.relics.mayo.MM_01_NezmingRelic;

import static reactkr.powers.mayo.AbstractBulletPower.BulletTrigger;

public abstract class AbstractAimedCard extends AbstractEasyCard_Mayo {

    private static final Logger logger = LogManager.getLogger(AbstractAimedCard.class.getName());

    // ── 서브클래스 노출 필드 ──────────────────────────────────
    protected int finalDamage;
    protected int finalMagic;

    protected boolean useAim   = false;
    protected boolean useQuick = false;

    public boolean usesDepletion    = false;
    protected boolean depleteOnTurnEnd = false;
    public int depletionMax         = 0;

    // ── 생성자 ───────────────────────────────────────────────
    public AbstractAimedCard(String cardID, int cost, CardType type,
                             CardRarity rarity, CardTarget target) {
        this(cardID, cost, type, rarity, target, Mayo.Enums.MAYO_COLOR);
    }

    public AbstractAimedCard(String cardID, int cost, CardType type,
                             CardRarity rarity, CardTarget target, CardColor color) {
        super(cardID, cost, type, rarity, target, color);
    }

    // ── 서브클래스 구현 인터페이스 ────────────────────────────
    public abstract void normalUse(AbstractPlayer p, AbstractMonster m);
    public void aimedUse(AbstractPlayer p, AbstractMonster m) { }
    public void quickUse(AbstractPlayer p, AbstractMonster m) { }

    /**
     * 이 카드가 총알을 소비하는 시나리오를 반환.
     * 총알을 쓰지 않는 카드는 null 반환.
     *
     * 임시 구현: 서브클래스에서 아래 중 하나를 반환
     *   BulletTrigger.AIMED    — 조준 시만
     *   BulletTrigger.QUICKED  — 속사 시만
     *   BulletTrigger.OR       — 조준 또는 속사 시
     *   BulletTrigger.ALWAYS   — 항상
     *   null                   — 총알 미사용
     */
    protected BulletTrigger bulletTrigger() {
        return null;
    }

    // ── 총알 소비 여부 판정 ───────────────────────────────────
    /**
     * 현재 상태에서 실제로 총알을 소비할지 최종 판정.
     * use() 와 데미지 계산 양쪽에서 동일한 기준으로 사용.
     */
    private boolean shouldConsumeBullet() {
        BulletTrigger trigger = bulletTrigger();
        if (trigger == null) return false;
        switch (trigger) {
            case AIMED:   return isAimed();
            case QUICKED: return isQuick();
            case OR:      return isAimed() || isQuick();
            case ALWAYS:  return true;
            default:      return false;
        }
    }

    // ── 카드 사용 흐름 ────────────────────────────────────────
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        normalUse(p, m);

        if (this.usesDepletion) triggerDepletionFlow();

        // 총알 소비는 ALWAYS / OR 시나리오 중복 방지를 위해
        // shouldConsumeBullet() 기준으로 1회만 처리
        boolean consumed = false;

        if (isAimed()) {
            if (!consumed && shouldConsumeBullet()) {
                usePower();
                consumed = true;
            }
            aimedUse(p, m);
        }
        if (isQuick()) {
            if (!consumed && shouldConsumeBullet()) {
                usePower();
                consumed = true;
            }
            quickUse(p, m);
        }
        // ALWAYS: 조준도 속사도 아닌 경우에도 소비
        if (!consumed && shouldConsumeBullet()) {
            usePower();
        }
    }

    @Override
    public void triggerOnEndOfPlayerTurn() {
        super.triggerOnEndOfPlayerTurn();
        if (this.usesDepletion && this.depleteOnTurnEnd) {
            this.superFlash();
            triggerDepletionFlow();
        }
    }

    // ── 총알 Power 헬퍼 ──────────────────────────────────────

    /** 장전된 총알 발사. fire() 내부에서 onFire() + 제거 처리됨. */
    protected void usePower() {
        AbstractBulletPower bullet =
                AbstractBulletPower.getLoaded(AbstractDungeon.player);
        if (bullet != null) bullet.fire();
    }

    /** 특정 클래스의 총알이 장전되어 있는지 확인. 데미지 계산에서 사용. */
    private boolean hasBulletPower(Class<? extends AbstractBulletPower> cls) {
        return AbstractBulletPower.isLoaded(AbstractDungeon.player, cls);
    }

    // ── 고갈(Depletion) 흐름 ─────────────────────────────────
    private void triggerDepletionFlow() {
        final AbstractCard self = this;
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                self.baseMagicNumber -= 1;
                self.magicNumber = self.baseMagicNumber;
                if (self.magicNumber <= 0) self.exhaust = true;
                this.isDone = true;
            }
        });
    }

    // ── 조준(Aimed) / 속사(Quick) 판정 ───────────────────────
    protected boolean isAimed() {
        AbstractPlayer p = AbstractDungeon.player;
        int threshold = 1;
        if (p.hasRelic(MM_01_NezmingRelic.ID)) threshold++;
        for (AbstractPower pow : p.powers)
            if (pow instanceof AbstractAddRangePower) threshold += pow.amount;
        int curIdx = p.hand.group.indexOf(this);
        if (curIdx == -1) return false;
        return curIdx < threshold;
    }

    protected boolean isQuick() {
        AbstractPlayer p = AbstractDungeon.player;
        int threshold = 1;
        if (p.hasRelic(MM_01_NezmingRelic.ID)) threshold++;
        for (AbstractPower pow : p.powers)
            if (pow instanceof AbstractAddRangePower)
                threshold += ((AbstractAddRangePower) pow).amount2;
        int curIdx = p.hand.group.indexOf(this);
        if (curIdx == -1) return false;
        return (p.hand.size() - 1 - curIdx) < threshold;
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = (isAimed() && useAim)    ? AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy()
                : (isQuick() && useQuick)  ? AbstractCard.GREEN_BORDER_GLOW_COLOR.cpy()
                :                            AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
    }

    // ── 데미지 계산 파이프라인 ────────────────────────────────

    private void applyPowerScalingAndBullets() {
        int powerDelta = this.damage - this.baseDamage;
        this.secondDamage = Math.max(0, this.baseSecondDamage + powerDelta);
        this.isSecondDamageModified = (this.secondDamage != this.baseSecondDamage);

        this.magicNumber = this.baseMagicNumber;
        this.secondMagic = this.baseSecondMagic;

        this.isCostModifiedForTurn = false;
        this.costForTurn = this.cost;

        // 총알 배율은 실제 소비 조건이 충족될 때만 미리보기에 반영
        if (!shouldConsumeBullet()) return;

        boolean sniperBullet = hasBulletPower(MM_B_01_SniperBulletPower.class);
        boolean lightBullet  = hasBulletPower(MM_B_02_LightBulletPower.class);

        if (sniperBullet) {
            this.damage       *= 2;
            this.secondDamage *= 2;
            this.isDamageModified       = true;
            this.isSecondDamageModified = true;
            this.costForTurn            = this.cost + 1;
            this.isCostModifiedForTurn  = true;
        } else if (lightBullet) {
            this.damage       = Math.max(0, this.damage / 2);
            this.secondDamage = Math.max(0, this.secondDamage / 2);
            this.isDamageModified       = true;
            this.isSecondDamageModified = true;
            if (this.cost > 0) this.costForTurn = this.cost - 1;
            this.isCostModifiedForTurn  = true;
        }
    }

    private void applyMonsterBulletModifiers(AbstractMonster m) {
        if (!shouldConsumeBullet()) return;

        boolean hpBullet = hasBulletPower(MM_B_03_HPBulletPower.class);
        boolean tcBullet = hasBulletPower(MM_B_04_TCBulletPower.class);

        if (hpBullet) {
            scaleDamage(m.currentBlock > 0);
        } else if (tcBullet) {
            scaleDamage(m.currentBlock == 0);
        }
    }

    private void scaleDamage(boolean doubled) {
        if (doubled) {
            this.damage       *= 2;
            this.secondDamage *= 2;
        } else {
            this.damage       = Math.max(0, this.damage / 2);
            this.secondDamage = Math.max(0, this.secondDamage / 2);
        }
        this.isDamageModified       = true;
        this.isSecondDamageModified = true;
    }

    private void computeFinalValues() {
        if (isAimed()) {
            finalDamage = this.secondDamage;
            finalMagic  = this.secondMagic;
        } else {
            finalDamage = (this.damage < this.secondDamage)
                    ? AbstractDungeon.cardRandomRng.random(this.damage, this.secondDamage)
                    : this.damage;
            finalMagic = (this.magicNumber < this.secondMagic)
                    ? AbstractDungeon.cardRandomRng.random(this.magicNumber, this.secondMagic)
                    : this.magicNumber;
        }
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        applyPowerScalingAndBullets();
        computeFinalValues();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        applyPowerScalingAndBullets();
        applyMonsterBulletModifiers(mo);
        computeFinalValues();
    }
}