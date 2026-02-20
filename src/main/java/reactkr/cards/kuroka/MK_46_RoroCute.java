package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.actions.AddRorokaHPAction;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.powers.kuroka.AbstractRorokaPower;
import reactkr.powers.kuroka.MK_11_RoroMajinai_Power;
import reactkr.powers.kuroka.MK_12_RoroCute_Power;

import static reactkr.ModFile.makeID;
import static reactkr.patches.RorokaTag.ROROKA;
import static reactkr.util.Wiz.applyToSelf;

public class MK_46_RoroCute extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("RoroCute");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public MK_46_RoroCute() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        baseSecondMagic = secondMagic = 1;
        tags.add(ROROKA);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractRorokaPower pow = (AbstractRorokaPower) p.getPower(MK_12_RoroCute_Power.POWER_ID);
        if (pow == null) {
            pow = new MK_12_RoroCute_Power(p, magicNumber);
            pow.amount2 = secondMagic;
        }
        //pow.additionalEffect();
        if (p.hasPower(MK_12_RoroCute_Power.POWER_ID)) {
            pow.amount += magicNumber;
            pow.amount2 += secondMagic;
        } else {
            applyToSelf(pow);
        }
        pow.updateDescription();
        addToBot(new AddRorokaHPAction(p, p, this.magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(3);
    }
}