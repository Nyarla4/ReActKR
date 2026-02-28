package reactkr.powers.kuroka;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import reactkr.powers.AbstractEasyPower;

import static reactkr.ModFile.makeID;

public class MK_20_PepZeLiPower extends AbstractEasyPower {
    public static final String POWER_ID = makeID("PepZeLiPower");
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public MK_20_PepZeLiPower(AbstractCreature owner, int energyAmt) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, Math.min(energyAmt, 999));

        this.updateDescription();
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        if (this.amount >= 999) {
            this.amount = 999;
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    public void onEnergyRecharge() {
        this.flash();
        AbstractDungeon.player.gainEnergy(this.amount);
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}