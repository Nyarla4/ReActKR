package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Mayo;

import static reactkr.ModFile.makeID;

public class MM_02_Worm extends AbstractEasyCard_Mayo {
    public final static String ID = makeID("Worm");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public MM_02_Worm() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 6;
        baseMagicNumber = magicNumber = 6;
        baseSecondMagic = secondMagic = 5;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        calculateDamage();
        if ((baseDamage - magicNumber) > 0) {
            this.loadCardImage("reactkrResources/images/cards/Worm2.png");
            this.name = cardStrings.EXTENDED_DESCRIPTION[0];
        } else {
            this.loadCardImage("reactkrResources/images/cards/Worm.png");
            this.name = cardStrings.NAME;
        }
    }
    //indexOf(this) < Math.max(1, hand.size() / 3)
    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    private void calculateDamage() {
        AbstractPlayer p = AbstractDungeon.player;
        if (p == null || p.hand == null) return;

        int curIdx = p.hand.group.indexOf(this);

        // 1. [구조적 예외 처리] 손패에 없는 경우 기본값
        if (curIdx == -1) {
            this.baseDamage = this.magicNumber;
            return;
        }

        // 1. [구조적 경계값 결정] 패 수량에 따른 최대 허용 인덱스 설정
        int handSize = p.hand.size();
        int closeThreshold;

        if (handSize >= 10) {
            closeThreshold = 3; // 10장: 0, 1, 2, 3까지
        } else if (handSize >= 7) {
            closeThreshold = 2; // 7, 8, 9장: 0, 1, 2까지
        } else if (handSize >= 5) {
            closeThreshold = 1; // 5, 6장: 0, 1까지
        } else {
            closeThreshold = 0; // 1~4장: 0까지
        }

        // 3. [판정 및 수치 산출 흐름]
        int bonus;
        if (curIdx == 0) {
            // [구조적 중심] 어떤 상황에서도 가장 가까운 0번은 최대 보너스
            bonus = this.secondMagic * 2;
        } else if (curIdx < closeThreshold) {
            // [상대적 근접] 전체 수량에 비례해 결정된 범위 내에 있을 때
            bonus = this.secondMagic;
        } else {
            // [멀리 있음]
            bonus = 0;
        }

        // 4. [최종 데이터 반영]
        this.baseDamage = this.magicNumber + bonus;
        this.isDamageModified = (bonus > 0);
    }
}