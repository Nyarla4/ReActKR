package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.patches.RorokaTag;
import reactkr.powers.DelayedPower;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.atb;

public class MK_98_RoroGaki extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("RoroGaki");
    // intellij stuff , self, special, , , , , 5,

    public MK_98_RoroGaki() {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        baseMagicNumber = magicNumber = 4;
        baseSecondMagic = secondMagic = 2;
        exhaust = true;
        tags.add(RorokaTag.ROROKA);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int loseHealth = magicNumber;
        int gainStrength = magicNumber;
        int keepTurns = secondMagic;

        //atb(new DamageAction(p, new DamageInfo(p, loseHealth, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
        atb(new ApplyPowerAction(p,p, new StrengthPower(p,gainStrength),gainStrength));
        atb(new ApplyPowerAction(p,p,new DelayedPower(p,keepTurns,new StrengthPower(p,-gainStrength)),keepTurns));
        //addToBot(new ApplyPowerAction(p,p, new GainStrengthPower(p, -gainStrength), -gainStrength));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}
