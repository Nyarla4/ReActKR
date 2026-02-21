package reactkr.powers.kuroka;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.powers.AbstractEasyPower;

import static reactkr.ModFile.makeID;

public class MK_19_Delusioning_Power extends AbstractEasyPower {
    public static final String POWER_ID = makeID("DelusioningPower");
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private static final Logger logger = LogManager.getLogger(MK_19_Delusioning_Power.class.getName());
    private boolean witchificated = false;

    public MK_19_Delusioning_Power(AbstractCreature owner) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, 1);
        updateDescription();
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        witchificated = false;
        if (owner.hasPower(MK_03_DelusionFactor_Power.POWER_ID)) {
            int based = owner.getPower(MK_03_DelusionFactor_Power.POWER_ID).amount;
            if (power.ID.equals(MK_03_DelusionFactor_Power.POWER_ID)) {
                based += power.amount;
            }
            if (based >= 10)
                witchificated = true;
        }
        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        if (owner.hasPower(MK_04_Witchification_Power.POWER_ID)) {
            addToBot(new GainBlockAction(owner, amount2));
        } else {
            addToBot(new ApplyPowerAction(owner, owner, new MK_03_DelusionFactor_Power(owner, amount), amount));
        }
    }

    public void updateDescription() {
        if (owner.hasPower(MK_04_Witchification_Power.POWER_ID) || witchificated) {
            this.name = DESCRIPTIONS[0];
            this.description = DESCRIPTIONS[2] + this.amount2 + DESCRIPTIONS[3];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[3];
        }
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
