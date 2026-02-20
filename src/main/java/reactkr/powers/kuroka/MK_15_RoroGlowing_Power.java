package reactkr.powers.kuroka;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.actions.AddRorokaHPAction;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.atb;

public class MK_15_RoroGlowing_Power extends AbstractRorokaPower {
    public static final String POWER_ID = makeID("RoroGlowingPower");
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private static final Logger logger = LogManager.getLogger(MK_15_RoroGlowing_Power.class.getName());

    public MK_15_RoroGlowing_Power(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, false, owner, amount);
    }

    @Override
    public void updateDescription() {
        super.updateDescription();
        this.description += DESCRIPTIONS[0] + this.amount2 + DESCRIPTIONS[1];
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }

    @Override
    public void additionalEffect() {
        addToBot(new AddRorokaHPAction(this.owner, this.owner, this.amount2));
    }
}
