package reactkr.powers.kuroka;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.TextAboveCreatureEffect;
import jdk.jpackage.internal.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.powers.AbstractEasyPower;

import static reactkr.ModFile.makeID;

public class MK_06_MajinaiAmplify_Power extends AbstractEasyPower {

    public static final String POWER_ID = makeID("MajinaiAmplifyPower");
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private static final Logger logger = LogManager.getLogger(MK_06_MajinaiAmplify_Power.class.getName());

    public MK_06_MajinaiAmplify_Power(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        updateDescription();
        if(this.owner.hasPower(MK_07_Destiny_Power.POWER_ID)){
            this.owner.getPower(MK_07_Destiny_Power.POWER_ID).updateDescription();
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void onApplyPower(AbstractPower p, AbstractCreature target, AbstractCreature source) {
        if (source == owner && p.ID.equals(MK_01_Majinai_Power.POWER_ID)) {
            //logger.info("amplified " + this.amount);
            p.amount += this.amount;
            this.flash();
        }
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
