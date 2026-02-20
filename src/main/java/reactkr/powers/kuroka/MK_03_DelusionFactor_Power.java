package reactkr.powers.kuroka;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.TextAboveCreatureEffect;
import reactkr.powers.AbstractEasyPower;

import static reactkr.ModFile.makeID;

public class MK_03_DelusionFactor_Power extends AbstractEasyPower {

    public static final String POWER_ID = makeID("DelusionFactorPower");

    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public MK_03_DelusionFactor_Power(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        if (this.amount >= 10 && !this.owner.hasPower(MK_04_Witchification_Power.POWER_ID)) {
            addToBot(new ApplyPowerAction(this.owner, this.owner, new MK_04_Witchification_Power(this.owner)));
        }
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + DESCRIPTIONS[1];
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        if (this.amount >= 10 && !this.owner.hasPower(MK_04_Witchification_Power.POWER_ID)) {
            addToBot(new ApplyPowerAction(this.owner, this.owner, new MK_04_Witchification_Power(this.owner)));
        }
        if (this.amount < 10 && this.owner.hasPower(MK_04_Witchification_Power.POWER_ID)) {
            MK_04_Witchification_Power witch = (MK_04_Witchification_Power) this.owner.getPower(MK_04_Witchification_Power.POWER_ID);
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, witch.ID));
        }
    }

    @Override
    public void onRemove() {
        super.onRemove();
        if (this.owner.hasPower(MK_04_Witchification_Power.POWER_ID)) {
            MK_04_Witchification_Power witch = (MK_04_Witchification_Power) this.owner.getPower(MK_04_Witchification_Power.POWER_ID);
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, witch.ID));
        }
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
