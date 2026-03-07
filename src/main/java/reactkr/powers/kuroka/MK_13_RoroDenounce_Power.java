package reactkr.powers.kuroka;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

import static reactkr.ModFile.makeID;

public class MK_13_RoroDenounce_Power extends AbstractRorokaPower {
    public static final String POWER_ID = makeID("RoroDenouncePower");
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private static final Logger logger = LogManager.getLogger(MK_13_RoroDenounce_Power.class.getName());

    public MK_13_RoroDenounce_Power(AbstractCreature owner, int amount) {
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
        ArrayList<AbstractMonster> aliveMonsters = new ArrayList<>();
        for (AbstractMonster mon : AbstractDungeon.getMonsters().monsters) {
            if (!mon.isDeadOrEscaped()) {
                aliveMonsters.add(mon);
            }
        }
        if (!aliveMonsters.isEmpty()) {
            int randomIndex = AbstractDungeon.miscRng.random(0, aliveMonsters.size() - 1);
            AbstractMonster randomMonster = aliveMonsters.get(randomIndex);

            if (randomMonster != null) {
                addToBot(new ApplyPowerAction(randomMonster, this.owner, new VulnerablePower(randomMonster, amount2, false)));
            }
        }
    }
}
