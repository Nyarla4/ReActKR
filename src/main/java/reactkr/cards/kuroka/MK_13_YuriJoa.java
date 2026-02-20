package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.powers.kuroka.MK_03_DelusionFactor_Power;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.applyToSelf;

public class MK_13_YuriJoa extends AbstractWitchificateCard {
    public final static String ID = makeID("YuriJoa");
    // intellij stuff skill, self, common, , , , , 1, 2

    public MK_13_YuriJoa() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        baseSecondMagic = secondMagic = 4;
        initializeWitchPreview();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(witchificated ? secondMagic : magicNumber));
        if (!witchificated) {
            applyToSelf(new MK_03_DelusionFactor_Power(p, 1));
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondMagic(1);
    }

    @Override
    void Witchification() {
        if (witchificated) {
            this.rawDescription = this.cardStrings.EXTENDED_DESCRIPTION[0];
        } else {
            this.rawDescription = this.cardStrings.DESCRIPTION;
        }
    }
}
