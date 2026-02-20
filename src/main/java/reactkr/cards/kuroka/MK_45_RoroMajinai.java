package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import reactkr.actions.AddRorokaHPAction;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.powers.kuroka.AbstractRorokaPower;
import reactkr.powers.kuroka.MK_11_RoroMajinai_Power;

import static reactkr.ModFile.makeID;
import static reactkr.patches.RorokaTag.ROROKA;
import static reactkr.util.Wiz.applyToSelf;

public class MK_45_RoroMajinai extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("RoroMajinai");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public MK_45_RoroMajinai() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        baseSecondMagic = secondMagic = 4;
        tags.add(ROROKA);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractRorokaPower pow = (AbstractRorokaPower) p.getPower(MK_11_RoroMajinai_Power.POWER_ID);
        if (pow == null) {
            pow = new MK_11_RoroMajinai_Power(p, magicNumber);
            pow.amount2 = secondMagic;
        }
        //pow.additionalEffect();
        if (p.hasPower(MK_11_RoroMajinai_Power.POWER_ID)) {
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