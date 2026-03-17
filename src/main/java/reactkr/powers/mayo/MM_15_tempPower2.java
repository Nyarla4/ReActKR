package reactkr.powers.mayo;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerToRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.actions.MajinaiDamageAction;
import reactkr.powers.AbstractEasyPower;
import reactkr.powers.kuroka.MK_01_Majinai_Power;
import reactkr.powers.kuroka.MK_02_MajinaiStrength_Power;
import reactkr.powers.kuroka.MK_18_SpicyNakjiKimchiJook_Power;
import reactkr.relics.kuroka.MK_04_MagicStickHammerRelic;

import static reactkr.ModFile.makeID;

public class MM_15_tempPower2 extends AbstractEasyPower {
    public static final String POWER_ID = makeID("tempPower2");
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public int minAmount;
    public int maxAmount;
    private static final Logger logger = LogManager.getLogger(MM_15_tempPower2.class.getName());

    public MM_15_tempPower2(AbstractCreature owner, int minAmount, int maxAmount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, 0);
        this.canGoNegative = true;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        AbstractCreature target = this.owner;
        this.addToBot(new ApplyPowerAction(target, target, new MM_03_EvasionPower(target, amount)));
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (!isPlayer) return;
        this.amount = AbstractDungeon.miscRng.random(minAmount, maxAmount);
        updateDescription();
    }

    @Override
    public void updateDescription() {
        if (this.amount == 0) {
            this.description = DESCRIPTIONS[3];//"내 턴 시작시 회피에 변화가 없습니다."
        } else {
            this.description = DESCRIPTIONS[0];//"내 턴 시작시 회피를 "
            if (this.amount > 0) {
                this.description += (this.amount + DESCRIPTIONS[1]);//" 얻습니다."
            } else {
                this.description += ((this.amount * -1) + DESCRIPTIONS[2]);//" 잃습니다."
            }
        }
        this.description += DESCRIPTIONS[4];//" 얻거나 잃을 회피의 값은 턴 종료시 결정됩니다."
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
