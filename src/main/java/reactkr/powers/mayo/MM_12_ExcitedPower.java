package reactkr.powers.mayo;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.actions.CardChangeAction;
import reactkr.cards.subcards.MM_50_NowYouNezming;
import reactkr.powers.AbstractEasyPower;

import static reactkr.ModFile.makeID;

public class MM_12_ExcitedPower extends AbstractEasyPower {
    public static final String POWER_ID = makeID("ExcitedPower");
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    private final AbstractCard target = new MM_50_NowYouNezming();
    private static final Logger logger = LogManager.getLogger(MM_12_ExcitedPower.class.getName());

    public MM_12_ExcitedPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
