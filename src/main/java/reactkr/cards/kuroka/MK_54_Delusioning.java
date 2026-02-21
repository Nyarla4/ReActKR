package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import reactkr.powers.AbstractEasyPower;
import reactkr.powers.kuroka.MK_03_DelusionFactor_Power;
import reactkr.powers.kuroka.MK_19_Delusioning_Power;
import reactkr.util.ProAudio;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.applyToSelf;
import static reactkr.util.Wiz.playAudio;

public class MK_54_Delusioning extends AbstractWitchificateCard {
    public final static String ID = makeID("Delusioning");
    // intellij stuff skill, self, common, , , , , 1, 2

    public MK_54_Delusioning() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.NONE);

        baseMagicNumber = magicNumber = 1;
        baseSecondMagic = secondMagic = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudio(ProAudio.DELUSIONING);
        AbstractPower pow = p.getPower(MK_19_Delusioning_Power.POWER_ID);
        if (pow == null) {
            pow = new MK_19_Delusioning_Power(p);
            ((AbstractEasyPower) pow).amount2 = secondMagic;
            applyToSelf(pow);
        } else {
            pow.amount += magicNumber;
            ((AbstractEasyPower) pow).amount2 += secondMagic;
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
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
