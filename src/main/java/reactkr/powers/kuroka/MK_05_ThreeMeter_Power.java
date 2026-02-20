package reactkr.powers.kuroka;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
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

public class MK_05_ThreeMeter_Power extends AbstractEasyPower {
    public static final String POWER_ID = makeID("ThreeMeterPower");

    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public int buff;

    private static final Logger logger = LogManager.getLogger(MK_05_ThreeMeter_Power.class.getName());

    public MK_05_ThreeMeter_Power(AbstractCreature owner, int thornsDamage) {
        super(POWER_ID,NAME,PowerType.BUFF,true,owner,thornsDamage);

        this.updateDescription();
        this.loadRegion("flameBarrier");

        if (owner.hasPower(MK_03_DelusionFactor_Power.POWER_ID)) {
            buff = owner.getPower(MK_03_DelusionFactor_Power.POWER_ID).amount;
        }
        updateDescription();
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        buff = 0;
        if (owner.hasPower(MK_03_DelusionFactor_Power.POWER_ID)) {
            buff = owner.getPower(MK_03_DelusionFactor_Power.POWER_ID).amount;
        }
        if(power.ID.equals(MK_03_DelusionFactor_Power.POWER_ID)){
            buff += power.amount;
        }
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + (this.amount * this.buff) + DESCRIPTIONS[1];
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != this.owner) {
            this.flash();
            if (owner.hasPower(MK_03_DelusionFactor_Power.POWER_ID)) {
                buff = owner.getPower(MK_03_DelusionFactor_Power.POWER_ID).amount;
            }
            this.addToTop(new DamageAction(info.owner, new DamageInfo(this.owner, this.amount * this.buff, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
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
