package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.Mayo;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.orbs.mayo.MM_01_SniperBulletOrb;
import reactkr.orbs.mayo.MM_02_LightBulletOrb;
import reactkr.orbs.mayo.MM_03_HPBulletOrb;
import reactkr.orbs.mayo.MM_04_TCBulletOrb;
import reactkr.powers.mayo.AbstractAddRangePower;
import reactkr.relics.mayo.MM_01_NezmingRelic;

public abstract class AbstractAimedCard extends AbstractEasyCard_Mayo {

    private static final Logger logger = LogManager.getLogger(AbstractAimedCard.class.getName());

    // ── 서브클래스 노출 필드 ────────────────────────────────────────────────────

    /** use() / aimedUse() 안에서 읽어 실제 데미지 처리에 사용 */
    protected int finalDamage;
    protected int finalMagic;

    /** true 이면 카드 좌단 조준 글로우 + aimedUse() 발동 */
    protected boolean useAim = false;

    /** true 이면 카드 우단 속사 글로우 + quickUse() 발동 */
    protected boolean useQuick = false;

    /**
     * true 이면 이 카드는 고갈(Depletion) 시스템을 사용합니다.
     * use() 시 magicNumber 1 감소 → 0 이하면 소멸(Exhaust).
     * 자식 클래스 생성자에서 this.usesDepletion = true 로 활성화합니다.
     */
    public boolean usesDepletion = false;

    /**
     * true 이면 턴 종료 시 패에 들고 있으면 고갈 수치가 깎입니다.
     * usesDepletion 이 false 면 이 값은 무시됩니다.
     */
    protected boolean depleteOnTurnEnd = false;

    /**
     * 고갈 시스템의 최대 탄창 수치. isReset 리셋 시 기준값으로 사용됩니다.
     * usesDepletion = true 인 경우 생성자에서 반드시 설정하십시오.
     * 업그레이드 시 upgradeMagicNumber() 와 함께 갱신해야 합니다.
     */
    public int depletionMax = 0;

    // ── 생성자 ────────────────────────────────────────────────────────────────

    public AbstractAimedCard(String cardID, int cost, CardType type,
                             CardRarity rarity, CardTarget target) {
        this(cardID, cost, type, rarity, target, Mayo.Enums.MAYO_COLOR);
    }

    public AbstractAimedCard(String cardID, int cost, CardType type,
                             CardRarity rarity, CardTarget target, CardColor color) {
        super(cardID, cost, type, rarity, target, color);
    }

    // ── 서브클래스 구현 인터페이스 ─────────────────────────────────────────────

    /** 조준 여부와 무관하게 항상 실행되는 기본 처리. */
    public abstract void normalUse(AbstractPlayer p, AbstractMonster m);

    /** 조준(Aimed) 상태일 때 추가 실행. 기본 구현은 없음. */
    public void aimedUse(AbstractPlayer p, AbstractMonster m) { }

    /** 속사(Quick) 상태일 때 추가 실행. 기본 구현은 없음. */
    public void quickUse(AbstractPlayer p, AbstractMonster m) { }

    /**
     * 조준/속사 판정에서 총알 오브를 소비할지 여부.
     * 총알 오브가 없는 카드는 false 를 반환하면 됩니다.
     */
    protected abstract boolean useBullet();

    // ── 카드 사용 흐름 ────────────────────────────────────────────────────────

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        normalUse(p, m);

        if (this.usesDepletion) {
            triggerDepletionFlow();
        }

        if (isAimed()) {
            if (useBullet()) useOrb();
            aimedUse(p, m);
        }
        if (isQuick()) {
            if (useBullet()) useOrb();
            quickUse(p, m);
        }
    }

    @Override
    public void triggerOnEndOfPlayerTurn() {
        super.triggerOnEndOfPlayerTurn();
        if (this.usesDepletion && this.depleteOnTurnEnd) {
            this.superFlash(); // "안 써서 깎였다"는 시각적 피드백
            triggerDepletionFlow();
        }
    }

    // ── 고갈(Depletion) 흐름 ─────────────────────────────────────────────────

    /**
     * magicNumber 감소와 소멸 판정을 단일 액션 틱 안에서 원자적으로 처리합니다.
     *
     * [설계 근거]
     * - baseMagicNumber 를 직접 수정해 "지금 손에 든 이 카드 인스턴스의 탄창"만 깎습니다.
     *   UUID 기반 액션(ModifiyMagicNumberAction)을 쓰면 덱·버린 덱의 동명 카드까지
     *   전부 깎이므로 총기 시스템 기획 의도와 맞지 않습니다.
     * - 감소와 exhaust 판정이 동일 틱에서 실행되므로 타이밍 엇박자가 없습니다.
     * - 액션 완료 후 엔진이 applyPowers() 를 자동 재호출하여 UI 를 올바르게 갱신합니다.
     *   isMagicNumberModified 를 별도로 세울 필요가 없습니다.
     */
    private void triggerDepletionFlow() {
        final AbstractCard self = this;
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                self.baseMagicNumber -= 1;
                self.magicNumber = self.baseMagicNumber;
                if (self.magicNumber <= 0) {
                    self.exhaust = true;
                }
                this.isDone = true;
            }
        });
    }

    // ── 조준(Aimed) / 속사(Quick) 판정 ───────────────────────────────────────

    protected boolean isAimed() {
        AbstractPlayer p = AbstractDungeon.player;
        int threshold = 1;
        if (p.hasRelic(MM_01_NezmingRelic.ID)) threshold++;
        for (AbstractPower pow : p.powers) {
            if (pow instanceof AbstractAddRangePower) threshold += pow.amount;
        }
        int curIdx = p.hand.group.indexOf(this);
        if (curIdx == -1) return false;
        return curIdx < threshold;
    }

    protected boolean isQuick() {
        AbstractPlayer p = AbstractDungeon.player;
        int threshold = 1;
        if (p.hasRelic(MM_01_NezmingRelic.ID)) threshold++;
        for (AbstractPower pow : p.powers) {
            if (pow instanceof AbstractAddRangePower)
                threshold += ((AbstractAddRangePower) pow).amount2;
        }
        int curIdx = p.hand.group.indexOf(this);
        if (curIdx == -1) return false;
        return (p.hand.size() - 1 - curIdx) < threshold;
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = (isAimed() && useAim)   ? AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy()
                : (isQuick() && useQuick) ? AbstractCard.GREEN_BORDER_GLOW_COLOR.cpy()
                : AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
    }

    // ── 탄환(Orb) 소비 및 헬퍼 ────────────────────────────────────────────────

    protected void useOrb() {
        AbstractDungeon.player.orbs.stream()
                .filter(orb -> !(orb instanceof EmptyOrbSlot)
                        && (orb.ID.equals(MM_01_SniperBulletOrb.ID)
                        || orb.ID.equals(MM_02_LightBulletOrb.ID)))
                .findFirst()
                .ifPresent(orb -> {
                    orb.passiveAmount -= 1;
                    orb.updateDescription();
                    orb.showEvokeValue();
                });
    }

    private boolean hasBulletOrb(String orbID) {
        return AbstractDungeon.player.orbs.stream()
                .anyMatch(orb -> !(orb instanceof EmptyOrbSlot) && orb.ID.equals(orbID));
    }

    // ── 데미지 계산 파이프라인 ─────────────────────────────────────────────────
    //
    // STS 엔진 호출 흐름:
    //   applyPowers()           마우스 오버 / 패 갱신 시
    //   calculateCardDamage(mo) 몬스터 타겟팅 시 (방어력·취약 포함)
    //
    // 두 경우 모두 super 가 baseDamage 를 출발점으로
    // Strength · Weak · Vigor 등을 반영해 this.damage 를 세팅합니다.
    //
    // [주의] super 호출 이후 this.damage = this.baseDamage 로 리셋하면
    //        파워 보정 전체가 날아갑니다.
    //        여기서는 super 결과 위에 배율만 곱합니다.

    /**
     * [Step 1] secondDamage 파워 보정 + sniper/light 총알 배율.
     *
     * super 는 this.damage 만 보정하므로 동일한 파워 델타를
     * secondDamage 에 수동 적용합니다.
     */
    private void applyPowerScalingAndBullets() {
        // secondDamage 파워 보정
        int powerDelta = this.damage - this.baseDamage;
        this.secondDamage = Math.max(0, this.baseSecondDamage + powerDelta);
        this.isSecondDamageModified = (this.secondDamage != this.baseSecondDamage);

        // super 가 건드리지 않는 커스텀 필드 리셋
        this.magicNumber = this.baseMagicNumber;
        this.secondMagic = this.baseSecondMagic;

        // 코스트 리셋
        this.isCostModifiedForTurn = false;
        this.costForTurn = this.cost;

        // sniper / light 총알 배율 — 조준 + useBullet() 일 때만
        if (!isAimed() || !useBullet()) return;

        boolean sniperBullet = hasBulletOrb(MM_01_SniperBulletOrb.ID);
        boolean lightBullet  = hasBulletOrb(MM_02_LightBulletOrb.ID);

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

    /**
     * [Step 2] HP/TC 총알 배율 — calculateCardDamage 전용.
     * Step 1 이후 호출되므로 sniper/light 배율 위에 추가로 곱해집니다.
     * 비조준 상태이거나 useBullet() == false 면 건너뜁니다.
     */
    private void applyMonsterBulletModifiers(AbstractMonster m) {
        if (!isAimed() || !useBullet()) return;

        boolean hpBullet = hasBulletOrb(MM_03_HPBulletOrb.ID);
        boolean tcBullet = hasBulletOrb(MM_04_TCBulletOrb.ID);

        if (hpBullet) {
            scaleDamage(m.currentBlock > 0);  // 방어막 있으면 2배, 없으면 0.5배
        } else if (tcBullet) {
            scaleDamage(m.currentBlock == 0); // 방어막 없으면 2배, 있으면 0.5배
        }
    }

    /** doubled == true 면 2배, false 면 내림 0.5배. */
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

    /**
     * [Step 3] finalDamage / finalMagic 확정.
     * 항상 갱신하여 이전 값이 잔류하는 버그를 원천 차단합니다.
     *
     * 조준 상태  : secondDamage / secondMagic (최댓값)
     * 비조준 상태: [damage, secondDamage] 범위 랜덤.
     *             범위가 역전·동일이면 damage / magicNumber 그대로 사용.
     */
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

    // ── STS 엔진 오버라이드 ───────────────────────────────────────────────────

    /** 마우스 오버 / 패 갱신 시 호출. super → this.damage = baseDamage + 파워 */
    @Override
    public void applyPowers() {
        super.applyPowers();           // ① this.damage = baseDamage + 파워
        applyPowerScalingAndBullets(); // ② secondDamage 보정 + sniper/light 총알
        computeFinalValues();          // ③ finalDamage / finalMagic 확정
    }

    /** 몬스터 타겟팅 시 호출. super → this.damage = baseDamage + 파워 + 몬스터 방어 */
    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);   // ① this.damage = baseDamage + 파워 + 몬스터 방어
        applyPowerScalingAndBullets();   // ② secondDamage 보정 + sniper/light 총알
        applyMonsterBulletModifiers(mo); // ③ HP/TC 총알
        computeFinalValues();            // ④ finalDamage / finalMagic 확정
    }
}