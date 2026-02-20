package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.powers.kuroka.MK_03_DelusionFactor_Power;
import reactkr.powers.kuroka.MK_05_ThreeMeter_Power;

import static reactkr.ModFile.makeID;

public class MK_17_ThreeMeter extends AbstractWitchificateCard {
    public final static String ID = makeID("ThreeMeter");
    // intellij stuff skill, self, common, , , , , 1, 2

    public MK_17_ThreeMeter() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 16;
        baseMagicNumber = magicNumber = 1;
        baseSecondMagic = secondMagic = 2;
        initializeWitchPreview();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int buff = 0;
        blck();
        int amount = witchificated ? secondMagic : magicNumber;
        this.addToBot(new ApplyPowerAction(p, p, new MK_05_ThreeMeter_Power(p, amount), amount));
    }

    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondMagic(1);
    }

    @Override
    void Witchification() {
        if (witchificated) {
            this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
        } else {
            this.rawDescription = cardStrings.DESCRIPTION;
        }
    }
}
