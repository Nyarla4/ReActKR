package reactkr.powers.mayo;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.powers.AbstractEasyPower;

public abstract class AbstractAddRangePower extends AbstractEasyPower {
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private static final Logger logger = LogManager.getLogger(AbstractAddRangePower.class.getName());

    public AbstractAddRangePower(String ID, String NAME, boolean isTurnBased, AbstractCreature owner, int addedAimRange, int addedQuickDrawRange) {
        super(ID, NAME, PowerType.BUFF, isTurnBased, owner, addedAimRange);
        this.amount2 = addedQuickDrawRange;
    }

    @Override
    public void updateDescription() {
        this.description = "";
        if (amount > 0) {
            this.description += "조준 범위가 " + amount + " 증가합니다.";
        }
        if (amount2 > 0) {
            this.description += "퀵드로 범위가 " + amount + " 증가합니다.";
        }
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
