package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.patches.RorokaTag;
import reactkr.util.ProAudio;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.playAudio;

public class MK_18_RorokaPunch extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("RorokaPunch");
    // intellij stuff attack, enemy, uncommon, X, , , , ,

    public MK_18_RorokaPunch() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 0;
        tags.add(RorokaTag.ROROKA);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudio(ProAudio.RORO_PUNCH);
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }

    public void upp() {
    }

    @Override
    public void applyPowers() {
        // 1. 데미지의 기준이 되는 baseDamage를 임시 체력 값으로 설정
        int tempHp = com.evacipated.cardcrawl.mod.stslib.patches.core.AbstractCreature.TempHPField.tempHp.get(AbstractDungeon.player);

        // 2. 강화 여부에 따라 배수 적용
        this.baseDamage = (int)((upgraded ? 1.0f : 0.5f) * tempHp);

        // 3. 이 함수를 호출해야 힘(Strength)이나 유물 효과가 계산된 최종 damage에 반영됩니다.
        super.applyPowers();

        String baseDesc = cardStrings.DESCRIPTION;
        if(AbstractDungeon.player != null) {
            String mulText = upgraded ? "만큼 " : "의 반만큼";
            this.rawDescription = "로로카의 체력"+ mulText +"(" + this.baseDamage + ")만큼 적에게 피해를 줍니다.";
        }

        this.initializeDescription();

        // 4. 숫자가 기본값과 다르다는 것을 보여주기 위해 설정 (숫자가 녹색으로 변함)
        this.isDamageModified = true;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        // 적의 취약(Vulnerable) 등을 계산하기 위해 이 메서드도 오버라이드 해줍니다.
        super.calculateCardDamage(mo);

        if(AbstractDungeon.player != null) {
            String mulText = upgraded ? "의 2배" : "";
            this.rawDescription = "로로카의 체력"+ mulText +"(" + this.baseDamage + ")만큼 적에게 피해를 줍니다.";
            this.initializeDescription();
        }
    }
}
