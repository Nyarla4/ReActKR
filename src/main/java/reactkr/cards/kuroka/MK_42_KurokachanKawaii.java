package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.ArmamentsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import reactkr.powers.kuroka.MK_03_DelusionFactor_Power;
import reactkr.util.ProAudio;

import java.util.ArrayList;

import static java.lang.Math.min;
import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.applyToSelf;
import static reactkr.util.Wiz.playAudio;

public class MK_42_KurokachanKawaii extends AbstractWitchificateCard {
    public final static String ID = makeID("KurokachanKawaii");
    // intellij stuff skill, self, common, , , , , 1, 2

    public MK_42_KurokachanKawaii() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.exhaust = true;
        baseMagicNumber = magicNumber = 3;
        initializeWitchPreview();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudio(ProAudio.KAWAIIERAITENSAI);
        if (!witchificated)
            applyToSelf(new MK_03_DelusionFactor_Power(p, magicNumber));
        this.addToBot(new ArmamentsAction(witchificated));
        if(witchificated && upgraded)
            this.addToBot(new DrawCardAction(1));
    }

    public void upp() {
        exhaust = false;
    }

    @Override
    void Witchification() {
        if (witchificated) {
            this.rawDescription = upgraded ? cardStrings.EXTENDED_DESCRIPTION[1] : cardStrings.EXTENDED_DESCRIPTION[0];
        } else {
            this.rawDescription = upgraded ? cardStrings.UPGRADE_DESCRIPTION : cardStrings.DESCRIPTION;
        }
    }
}
