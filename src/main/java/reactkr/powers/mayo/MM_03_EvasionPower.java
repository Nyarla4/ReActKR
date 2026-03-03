package reactkr.powers.mayo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.StrengthPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.powers.AbstractEasyPower;
import reactkr.powers.DelayedPower;

import static reactkr.ModFile.makeID;

public class MM_03_EvasionPower extends AbstractEasyPower {

    public static final String POWER_ID = makeID("EvasionPower");
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private static final Logger logger = LogManager.getLogger(MM_03_EvasionPower.class.getName());
    private static final int maxAmount = 100;

    public MM_03_EvasionPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, true, owner, amount);
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != this.owner) {

            float ran = AbstractDungeon.cardRandomRng.random(1f,100f);
            logger.info("ran: "+ran);
            if (ran < this.amount) {
                this.flash();
                this.amount /= 2;
                return 0;
            }
        }

        return damageAmount;
    }

    @Override
    public void stackPower(int amount){
        this.amount = Math.min(amount + this.amount, maxAmount);
    }

    @Override
    public void updateDescription() {
        this.description = this.amount + DESCRIPTIONS[0];
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}