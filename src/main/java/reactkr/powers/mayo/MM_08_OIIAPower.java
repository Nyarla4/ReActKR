package reactkr.powers.mayo;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import reactkr.powers.AbstractEasyPower;

import static reactkr.ModFile.makeID;

public class MM_08_OIIAPower extends AbstractEasyPower {

    public static final String POWER_ID = makeID("OIIAPower");
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public MM_08_OIIAPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void atStartOfTurnPostDraw() {
        addToBot(new DrawCardAction(amount));
        addToBot(new GainEnergyAction(amount));
        //addToBot(new SelectDiscardAction(amount));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}