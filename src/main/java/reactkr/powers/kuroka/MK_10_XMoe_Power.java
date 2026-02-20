package reactkr.powers.kuroka;

import com.evacipated.cardcrawl.mod.stslib.patches.core.AbstractCreature.TempHPField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.powers.AbstractEasyPower;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.atb;

public class MK_10_XMoe_Power extends AbstractEasyPower {
    public static final String POWER_ID = makeID("XMoePower");
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private static final Logger logger = LogManager.getLogger(MK_10_XMoe_Power.class.getName());
    private int startHp = 0;

    public MK_10_XMoe_Power(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        updateDescription();
        startHp = owner.currentHealth;
        this.isPostActionPower = true;
    }

    @Override
    public void atStartOfTurn() {
        if(startHp > owner.currentHealth){
            this.addToTop(new ApplyPowerAction(this.owner, this.owner, new MK_03_DelusionFactor_Power(this.owner, this.amount), this.amount));
        }
        this.addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, POWER_ID));
    }

//    @Override
//    public void wasHPLost(DamageInfo info, int damageAmount) {
//        if (damageAmount <= 0) {
//            return;
//        }
//
//        int curRoroHp = TempHPField.tempHp.get(this.owner);
//        if (curRoroHp > 0 && curRoroHp >= damageAmount) {
//            return;
//        }
//
//        this.flash();
//        this.addToTop(new ApplyPowerAction(this.owner, this.owner, new MK_03_DelusionFactor_Power(this.owner, this.amount * damageAmount), this.amount * damageAmount));
//    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
