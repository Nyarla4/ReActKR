package reactkr.powers.latte;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.powers.AbstractEasyPower;
import reactkr.stances.latte.MochaStance;

import static reactkr.ModFile.makeID;

public class AL_01_NoBadWordPower extends AbstractEasyPower {

    public static final String POWER_ID = makeID("NoBadWordPower");
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private static final Logger logger = LogManager.getLogger(AL_01_NoBadWordPower.class.getName());

    private int guard;

    public AL_01_NoBadWordPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        guard = AbstractDungeon.player.stance.ID.equals(MochaStance.STANCE_ID) ? 1 : 0;
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + (this.amount) + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new GainEnergyAction(amount));
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
    }

    @Override
    public void wasHPLost(DamageInfo info, int damageAmount) {
        if (damageAmount > 0 && info.type != DamageInfo.DamageType.HP_LOSS) {
            if (guard > 0) {
                guard--;
            } else {
                addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
            }
        }
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}