package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.powers.LambdaPower;
import reactkr.powers.kuroka.MK_03_DelusionFactor_Power;
import reactkr.powers.kuroka.MK_04_Witchification_Power;
import reactkr.powers.kuroka.MK_09_SecomMasada_Power;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.applyToSelf;
import static reactkr.util.Wiz.atb;

public class MK_06_SecomMasada extends AbstractWitchificateCard {
    public final static String ID = makeID("SecomMasada");
    // intellij stuff power, self, uncommon, , , , , ,

    public MK_06_SecomMasada() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        baseSecondMagic = secondMagic = 6;
        initializeWitchPreview();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new MK_09_SecomMasada_Power(p, magicNumber, secondMagic));
    }

    public void upp() {
        updateCost(-1);
    }

    @Override
    public void Witchification() {
        if (witchificated) {
            this.rawDescription = this.cardStrings.EXTENDED_DESCRIPTION[0];
        } else {
            this.rawDescription = this.cardStrings.DESCRIPTION;
        }
    }
}
