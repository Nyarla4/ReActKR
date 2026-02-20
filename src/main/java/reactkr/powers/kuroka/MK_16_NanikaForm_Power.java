package reactkr.powers.kuroka;

import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.potions.*;
import reactkr.potions.nanikaSpecial.*;
import reactkr.powers.AbstractEasyPower;

import java.util.ArrayList;

import static reactkr.ModFile.makeID;

public class MK_16_NanikaForm_Power extends AbstractEasyPower {
    public static final String POWER_ID = makeID("NanikaFormPower");
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private static final Logger logger = LogManager.getLogger(MK_16_NanikaForm_Power.class.getName());

    private static final ArrayList<AbstractPotion> nanikaPotions = new ArrayList<AbstractPotion>(){{
        add(new NanikaSaintWater());

        add(new NanikaFirePotion());
        add(new NanikaExplosivePotion());
        add(new NanikaWeakenPotion());
        add(new NanikaFearPotion());
        add(new NanikaBlockPotion());
        add(new NanikaStrengthPotion());
        add(new NanikaDexterityPotion());
        add(new NanikaSteroidPotion());
        add(new NanikaSpeedPotion());
        add(new NanikaEnergyPotion());
        add(new NanikaSwiftPotion());
        add(new NanikaAttackPotion());
        add(new NanikaSkillPotion());
        add(new NanikaPowerPotion());
        add(new NanikaColorlessPotion());
        add(new NanikaBlessPotion());

        add(new NanikaRegenPotion());
        add(new NanikaAncientPotion());
        add(new NanikaLiquidBronzePotion());
        add(new NanikaGamblingPotion());
        add(new NanikaSteelPotion());
        add(new NanikaDuplicationPotion());
        add(new NanikaChaosPotion());
        add(new NanikaMemoriesPotion());

        add(new NanikaSneckoPotion());
        add(new NanikaSmokeBomb());

        add(new NanikaMajinaiPotion());
        add(new NanikaDelusionPotion());
        add(new NanikaRoroconPotion());
    }};

    public MK_16_NanikaForm_Power(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        for (int i = 0; i < amount; i++) {
            AbstractPotion p = nanikaPotions.get(AbstractDungeon.cardRandomRng.random(nanikaPotions.size() - 1)).makeCopy();
            this.addToBot(new ObtainPotionAction(p));
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
