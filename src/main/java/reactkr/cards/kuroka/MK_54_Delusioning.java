package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import reactkr.powers.kuroka.MK_19_Delusioning_Power;
import reactkr.util.ProAudio;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.applyToSelf;
import static reactkr.util.Wiz.playAudio;

public class MK_54_Delusioning extends AbstractWitchificateCard {
    public final static String ID = makeID("Delusioning");
    // intellij stuff skill, self, common, , , , , 1, 2

    public MK_54_Delusioning() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);

        baseMagicNumber = magicNumber = 1;

        initializeWitchPreview();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudio(ProAudio.DELUSIONING);
        AbstractPower pow = p.getPower(MK_19_Delusioning_Power.POWER_ID);
        if (pow == null) {
            pow = new MK_19_Delusioning_Power(p);
            applyToSelf(pow);
        } else {
            pow.amount += magicNumber;
            pow.updateDescription();
        }
    }

    public void upp() {
        updateCost(-1);
    }

    @Override
    void Witchification() {
        if (witchificated) {
            this.name = cardStrings.EXTENDED_DESCRIPTION[0];
            this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[1];
        } else {
            this.name = cardStrings.NAME;
            this.rawDescription = cardStrings.DESCRIPTION;
        }
    }
}
