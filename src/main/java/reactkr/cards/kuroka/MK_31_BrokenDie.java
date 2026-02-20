package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.powers.kuroka.MK_01_Majinai_Power;

import static reactkr.ModFile.makeID;

public class MK_31_BrokenDie extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("BrokenDie");
    // intellij stuff skill, self, common, , , , , 1, 2

    public MK_31_BrokenDie() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 10;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        damage = m.getPower(MK_01_Majinai_Power.POWER_ID).amount;
        if(upgraded){
            damage *= 2;
        }
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        addToBot(new RemoveSpecificPowerAction(m,p,MK_01_Majinai_Power.POWER_ID));
    }

    public void upp() {
        //use에서 처리
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canuse = super.canUse(p, m);
        if (!canuse) return false;

        if (m != null) {
            if (!m.hasPower(MK_01_Majinai_Power.POWER_ID)) {
                this.cantUseMessage = "주문에 걸려있지 않습니다.";
                return false;
            }
        }

        return true;
    }
}
