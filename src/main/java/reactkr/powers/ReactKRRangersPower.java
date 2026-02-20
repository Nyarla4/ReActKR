package reactkr.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static reactkr.ModFile.makeID;

public class ReactKRRangersPower extends AbstractEasyPower {
    public static final String POWER_ID = makeID("ReactKRRangersPower");
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public ReactKRRangersPower(AbstractCreature owner) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, -1);
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
