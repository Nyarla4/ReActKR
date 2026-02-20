package reactkr.powers.kuroka;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.powers.AbstractEasyPower;

import static reactkr.ModFile.makeID;

public class MK_07_Destiny_Power extends AbstractEasyPower {
    public static final String POWER_ID = makeID("DestinyPower");
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private static final Logger logger = LogManager.getLogger(MK_07_Destiny_Power.class.getName());
    private int buff;

    public MK_07_Destiny_Power(AbstractCreature owner, int buff) {
        super(POWER_ID, NAME, AbstractPower.PowerType.BUFF, false, owner, 1);
        this.buff = buff;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        int amplified = 0;
        if(this.owner.hasPower(MK_06_MajinaiAmplify_Power.POWER_ID)){
            amplified = this.owner.getPower(MK_06_MajinaiAmplify_Power.POWER_ID).amount;
        }
        this.description = DESCRIPTIONS[0] + (this.amount * this.buff + amplified) + DESCRIPTIONS[1];
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != this.owner) {
            this.flash();
            this.addToBot(new ApplyPowerAction((AbstractMonster) info.owner, this.owner,
                    new MK_01_Majinai_Power(info.owner, buff * this.amount), buff * this.amount));
        }
        return damageAmount;
    }

    @Override
    public void atStartOfTurn() {
        this.addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, POWER_ID));
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
