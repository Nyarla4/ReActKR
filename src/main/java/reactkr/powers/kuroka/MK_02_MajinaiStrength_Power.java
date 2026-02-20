package reactkr.powers.kuroka;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.TextAboveCreatureEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.powers.AbstractEasyPower;

import static reactkr.ModFile.makeID;

public class MK_02_MajinaiStrength_Power extends AbstractEasyPower {

    public static final String POWER_ID = makeID("MajinaiStrengthPower");

    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private static final Logger logger = LogManager.getLogger(MK_02_MajinaiStrength_Power.class.getName());

    public MK_02_MajinaiStrength_Power(AbstractCreature owner) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, 1);
    }

//    @Override
//    public void onInitialApplication() {
//        majinaiUpdate();
//    }

    @Override
    public void stackPower(int amount) {
        majinaiUpdate();
    }

    public void majinaiUpdate() {
        int newAmount = (int) AbstractDungeon.getCurrRoom().monsters.monsters
                .stream().filter(f -> !f.isDeadOrEscaped())
                .filter(f -> f.hasPower(MK_01_Majinai_Power.POWER_ID))
                .count();
        //logger.info("strength update" + newAmount);
        this.amount = newAmount;
        updateDescription();
        if (this.amount <= 0) {
            addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
        }
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        if (type == DamageInfo.DamageType.NORMAL && this.amount > 0) {
            return damage + this.amount;
        }
        return damage;
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void onRemove() {
        super.onRemove();

        AbstractDungeon.effectList.add(
                new TextAboveCreatureEffect(
                        this.owner.hb.cX,
                        this.owner.hb.cY,
                        "지옥에 떨어져",
                        Settings.RED_TEXT_COLOR
                )
        );
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
