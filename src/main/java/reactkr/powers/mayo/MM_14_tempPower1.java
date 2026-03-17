package reactkr.powers.mayo;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.cards.subcards.MM_50_NowYouNezming;
import reactkr.powers.AbstractEasyPower;

import static reactkr.ModFile.makeID;

public class MM_14_tempPower1 extends AbstractEasyPower {
    public static final String POWER_ID = makeID("tempPower1");
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    private static final Logger logger = LogManager.getLogger(MM_14_tempPower1.class.getName());

    public MM_14_tempPower1(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        this.canGoNegative = true;
        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        AbstractCreature target = this.owner;
        this.addToBot(new ApplyPowerAction(target, target, new MM_03_EvasionPower(target, amount)));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
        if (this.amount > 0) {
            this.description += (this.amount + DESCRIPTIONS[1]);
        } else {
            this.description += ((this.amount * -1) + DESCRIPTIONS[2]);
        }
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
