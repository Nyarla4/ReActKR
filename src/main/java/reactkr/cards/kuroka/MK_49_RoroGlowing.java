package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.actions.AddRorokaHPAction;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.powers.kuroka.AbstractRorokaPower;
import reactkr.powers.kuroka.MK_15_RoroGlowing_Power;

import static reactkr.ModFile.makeID;
import static reactkr.patches.RorokaTag.ROROKA;
import static reactkr.util.Wiz.applyToSelf;

public class MK_49_RoroGlowing extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("RoroGlowing");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public MK_49_RoroGlowing() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        baseSecondMagic = secondMagic = 3;
        tags.add(ROROKA);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractRorokaPower pow = (AbstractRorokaPower) p.getPower(MK_15_RoroGlowing_Power.POWER_ID);
        if (pow == null) {
            pow = new MK_15_RoroGlowing_Power(p, magicNumber);
            pow.amount2 = secondMagic;
        }
        //pow.additionalEffect();
        if (p.hasPower(MK_15_RoroGlowing_Power.POWER_ID)) {
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