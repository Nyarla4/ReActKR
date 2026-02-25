package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import reactkr.powers.kuroka.MK_03_DelusionFactor_Power;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.applyToSelf;

public class MK_41_Impossible extends AbstractWitchificateCard {
    public final static String ID = makeID("Impossible");
    // intellij stuff skill, self, common, , , , , 1, 2

    public MK_41_Impossible() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 2;
        baseSecondMagic = secondMagic = 4;
        baseBlock = block = 10;
        initializeWitchPreview();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractPower pow = AbstractDungeon.player.getPower(MK_03_DelusionFactor_Power.POWER_ID);
        if(pow == null)
            return;

        int delusionLoseAmount = min((witchificated?secondMagic:magicNumber), pow.amount);

        if (!witchificated) {
            addToBot(new ApplyPowerAction(p, p, new MK_03_DelusionFactor_Power(p, -delusionLoseAmount)));
            addToBot(new GainBlockAction(p, delusionLoseAmount * block));
        } else {
            addToBot(new ApplyPowerAction(p, p, new MK_03_DelusionFactor_Power(p, -delusionLoseAmount)));
            //applyToSelf(new IntangiblePlayerPower(p, 1));
            addToBot(new GainBlockAction(p, 30));
            applyToSelf(new BlurPower(AbstractDungeon.player, upgraded ? 2 : 1));//흐릿함(다음 턴 시작시 방어도 잃지않음)
        }
    }

    public void upp() {
        upgradeBlock(4);
    }

    @Override
    void Witchification() {
        if (witchificated) {
            this.name = cardStrings.EXTENDED_DESCRIPTION[0];
            this.rawDescription = upgraded ? cardStrings.EXTENDED_DESCRIPTION[2] : cardStrings.EXTENDED_DESCRIPTION[1];
        } else {
            this.name = cardStrings.NAME;
            this.rawDescription = cardStrings.DESCRIPTION;
        }
    }
}
