package reactkr.powers.siah;

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

public class AS_00_MPower extends AbstractEasyPower {

    public static final String POWER_ID = makeID("MPower");
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private static final Logger logger = LogManager.getLogger(AS_00_MPower.class.getName());

    private int damaged;

    public AS_00_MPower(AbstractCreature owner) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, 1);
        damaged = 0;
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + (this.amount * damaged) + DESCRIPTIONS[0];
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new HealAction(this.owner, this.owner, damaged));
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        damaged += damageAmount;
        updateDescription();
        return damageAmount;
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}