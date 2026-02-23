package reactkr.powers.kuroka;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.powers.AbstractEasyPower;

import static reactkr.ModFile.makeID;

public class MK_08_NeredKuroka_Power extends AbstractEasyPower {
    public static final String POWER_ID = makeID("NeredKurokaPower");
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private static final Logger logger = LogManager.getLogger(MK_08_NeredKuroka_Power.class.getName());

    public MK_08_NeredKuroka_Power(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void updateDescription() {
        int amplified = 0;
        if (this.owner.hasPower(MK_06_MajinaiAmplify_Power.POWER_ID)) {
            amplified = this.owner.getPower(MK_06_MajinaiAmplify_Power.POWER_ID).amount;
        }

        this.description = DESCRIPTIONS[0] + (this.amount + amplified) + DESCRIPTIONS[1];
    }

    @Override
    public void onAfterCardPlayed(AbstractCard card) {
        this.flash();

        for (AbstractMonster mon : AbstractDungeon.getMonsters().monsters) {
            this.addToBot(new ApplyPowerAction(mon, owner,
                    new MK_01_Majinai_Power(mon, this.amount), this.amount));
        }
    }

//    @Override
//    public void atStartOfTurn() {
//        this.addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, POWER_ID));
//    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
