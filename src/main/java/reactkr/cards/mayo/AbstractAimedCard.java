package reactkr.cards.mayo;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.Mayo;
import reactkr.actions.ModifiyMagicNumberAction;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.orbs.mayo.MM_01_SniperBulletOrb;
import reactkr.orbs.mayo.MM_02_LightBulletOrb;
import reactkr.orbs.mayo.MM_03_HPBulletOrb;
import reactkr.orbs.mayo.MM_04_TCBulletOrb;
import reactkr.relics.mayo.MM_01_NezmingRelic;

public abstract class AbstractAimedCard extends AbstractEasyCard_Mayo {
    protected int finalDamage;
    private static final Logger logger = LogManager.getLogger(AbstractAimedCard.class.getName());
    protected boolean useAim = false;
    protected boolean useQuick = false;

    public AbstractAimedCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        this(cardID, cost, type, rarity, target, Mayo.Enums.MAYO_COLOR);
    }

    public AbstractAimedCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color) {
        super(cardID, cost, type, rarity, target, color);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        normalUse(p, m);
        // 고갈인 경우 고갈 처리
        if (basicDepletion() != -1) {
            addToBot(new ModifiyMagicNumberAction(this.uuid, -1));
        }
        // 조준 중에
        if (isAimed()) {
            // 탄을 쓰는 경우 탄 소모 처리
            if (useBullet()) {
                useOrb();
            }
            aimedUse(p, m);
        }
        if (isQuick()) {
            // 탄을 쓰는 경우 탄 소모 처리
            if (useBullet()) {
                useOrb();
            }
            quickUse(p, m);
        }
    }

    abstract public void normalUse(AbstractPlayer p, AbstractMonster m);

    /// 조준 추가 효과
    public void aimedUse(AbstractPlayer p, AbstractMonster m) {

    }

    /// 퀵드로 추가 효과
    public void quickUse(AbstractPlayer p, AbstractMonster m) {

    }

    /// 퀵드로 여부
    protected boolean isQuick() {
        AbstractPlayer p = AbstractDungeon.player;
        // 유물 보유 여부에 따른 거리(n) 결정 로직을 구조적으로 분리
        int threshold = p.hasRelic(MM_01_NezmingRelic.ID) ? 3 : 2;
        int curIdx = p.hand.group.indexOf(this);

        if (curIdx == -1) return false;

        int distRight = (p.hand.size() - 1) - curIdx;
        return distRight < threshold;
    }

    /// 조준 여부
    protected boolean isAimed() {
        AbstractPlayer p = AbstractDungeon.player;
        // 유물 보유 여부에 따른 거리(n) 결정 로직을 구조적으로 분리
        int threshold = p.hasRelic(MM_01_NezmingRelic.ID) ? 3 : 2;
        int curIdx = p.hand.group.indexOf(this);

        if (curIdx == -1) return false;

        // 흐름 제어: 왼쪽부터의 거리는 곧 인덱스(curIdx)와 같으므로,
        // 인덱스가 임계값(threshold)보다 작은지만 확인하면 됩니다.
        return curIdx < threshold;
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = isAimed()&&useAim ?
                AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy() :
                isQuick()&&useQuick ?
                AbstractCard.GREEN_BORDER_GLOW_COLOR.cpy() :
                AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
    }

    protected abstract boolean useBullet();

    protected void useOrb() {
        // 1. [흐름] 플레이어의 모든 구체 슬롯을 순회
        for (AbstractOrb orb : AbstractDungeon.player.orbs) {
            // 2. [구조 확인] 빈 슬롯이 아니고, 찾고 있는 특정 ID의 구체인지 확인
            if (!(orb instanceof EmptyOrbSlot) &&
                    (orb.ID.equals(MM_01_SniperBulletOrb.ID) ||
                            orb.ID.equals(MM_02_LightBulletOrb.ID)
                    )) {

                // 3. [데이터 수정] amount(passiveAmount)를 1 감소
                // 슬더스 기본 구체들은 passiveAmount를 주 수치로 사용합니다.
                orb.passiveAmount -= 1;

                // 4. [흐름 갱신] 수치가 변했으므로 시각적 요소와 텍스트를 업데이트
                orb.updateDescription(); // 마우스를 올렸을 때 나오는 설명 갱신

                // 5. [피드백] 수치가 변했음을 유저가 인지하도록 효과 추가
                orb.showEvokeValue();    // 수치 변화 연출
                break; // 루프 종료
            }
        }
    }

    /// 피해량 계산
    private void applyCustomModifiers() {
        if (!isAimed()) {
            finalDamage = AbstractDungeon.cardRandomRng.random(damage, secondDamage);
            return;
        }
        if (useBullet()) {
            boolean sniperBullet = AbstractDungeon.player.orbs.stream()
                    .anyMatch(orb -> !(orb instanceof EmptyOrbSlot) && orb.ID.equals(MM_01_SniperBulletOrb.ID));
            boolean lightBullet = AbstractDungeon.player.orbs.stream()
                    .anyMatch(orb -> !(orb instanceof EmptyOrbSlot) && orb.ID.equals(MM_02_LightBulletOrb.ID));

            if (sniperBullet) {
                this.damage *= 2;
                this.secondDamage *= 2;
                this.isDamageModified = true;
                this.isSecondDamageModified = true;

                // 코스트 변경 흐름
                this.costForTurn = this.cost + 1;
                this.isCostModifiedForTurn = true;
            } else if (lightBullet) {
                this.damage /= 2;
                this.secondDamage /= 2;
                this.isDamageModified = true;
                this.isSecondDamageModified = true;

                // 코스트 변경 흐름
                if (this.cost > 0) {
                    this.costForTurn = this.cost - 1;
                }
                this.isCostModifiedForTurn = true;
            } else {
                // 조건 미충족 시 복구 구조
                this.costForTurn = this.cost;
                this.isCostModifiedForTurn = false;
            }
        }
        finalDamage = secondDamage;
    }

    /// 피해량 계산
    private void applyCustomModifiers(AbstractMonster m) {
        boolean hpBullet = AbstractDungeon.player.orbs.stream()
                .anyMatch(orb -> !(orb instanceof EmptyOrbSlot) && orb.ID.equals(MM_03_HPBulletOrb.ID));
        boolean tcBullet = AbstractDungeon.player.orbs.stream()
                .anyMatch(orb -> !(orb instanceof EmptyOrbSlot) && orb.ID.equals(MM_04_TCBulletOrb.ID));

        if (hpBullet) {
            if (m.currentBlock > 0) {
                this.damage *= 2;
                this.secondDamage *= 2;
            } else {
                this.damage /= 2;
                this.secondDamage /= 2;
            }
            this.isDamageModified = true;
            this.isSecondDamageModified = true;

        } else if (tcBullet) {
            if (m.currentBlock == 0) {
                this.damage *= 2;
                this.secondDamage *= 2;
            } else {
                this.damage /= 2;
                this.secondDamage /= 2;
            }
            this.isDamageModified = true;
            this.isSecondDamageModified = true;
        } else {
            // 조건 미충족 시 복구 구조
            this.costForTurn = this.cost;
            this.isCostModifiedForTurn = false;
        }

        if (isAimed()) {
            finalDamage = secondDamage;
        } else {
            finalDamage = AbstractDungeon.cardRandomRng.random(damage, secondDamage);
        }
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        applyCustomModifiers();
        if (basicDepletion() != -1) {
            depletion();
        }
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        applyCustomModifiers();
        applyCustomModifiers(mo);
    }

    /// 고갈 초기치
    /// 고갈하지 않으면 -1
    public abstract int basicDepletion();

    /// 고갈 소멸
    protected void depletion() {
        if (magicNumber == 1) {
            exhaust = true;
        }
    }
}
