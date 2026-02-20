package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import reactkr.powers.kuroka.MK_03_DelusionFactor_Power;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.applyToSelf;

public class MK_43_DiaTomo extends AbstractWitchificateCard {
    public final static String ID = makeID("DiaTomo");
    // intellij stuff skill, self, common, , , , , 1, 2

    public MK_43_DiaTomo() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.NONE);
        baseMagicNumber = magicNumber = 2;
        initializeWitchPreview();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int gainDex = magicNumber;
        if (witchificated) {
            gainDex = p.getPower(MK_03_DelusionFactor_Power.POWER_ID).amount / 2;
        }
        applyToSelf(new DexterityPower(p, gainDex));
        AbstractPower pow = null;
        if (p.hasPower(MK_03_DelusionFactor_Power.POWER_ID)) {
            pow = p.getPower(MK_03_DelusionFactor_Power.POWER_ID);
            if (pow.amount >= 5) {
                applyToSelf(new BufferPower(p, 1));
            }
        }
    }

    public void upp() {
        upgradeBaseCost(1);
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
