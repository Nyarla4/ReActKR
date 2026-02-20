package reactkr.powers.uru;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.powers.AbstractEasyPower;

import static reactkr.ModFile.makeID;

public class MU_00_RainPower extends AbstractEasyPower {

    public static final String POWER_ID = makeID("RainPower");
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private static final Logger logger = LogManager.getLogger(MU_00_RainPower.class.getName());

    public MU_00_RainPower(AbstractCreature owner) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, 1);
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + DESCRIPTIONS[0];
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}