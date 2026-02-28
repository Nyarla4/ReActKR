package reactkr.powers.kuroka;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerToRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.TextAboveCreatureEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.actions.MajinaiDamageAction;
import reactkr.powers.AbstractEasyPower;
import reactkr.relics.kuroka.MK_04_MagicStickHammerRelic;

import static reactkr.ModFile.makeID;

public class MK_01_Majinai_Power extends AbstractEasyPower {

    public static final String POWER_ID = makeID("MajinaiPower");
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private static int ampified = 0;
    private static final Logger logger = LogManager.getLogger(MK_01_Majinai_Power.class.getName());

    public MK_01_Majinai_Power(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.DEBUFF, true, owner, amount);
        if (AbstractDungeon.player.hasPower(MK_06_MajinaiAmplify_Power.POWER_ID)) {
            ampified = AbstractDungeon.player.getPower(MK_06_MajinaiAmplify_Power.POWER_ID).amount;
        }
    }

    @Override
    public void onInitialApplication() {
        addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new MK_02_MajinaiStrength_Power(AbstractDungeon.player)));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount;
    }

    @Override
    public void atEndOfRound() {
        int overKill = this.amount - this.owner.currentHealth;

        addToBot(new MajinaiDamageAction(
                this.owner,
                new DamageInfo(AbstractDungeon.player, this.amount, DamageInfo.DamageType.HP_LOSS)
        ));

        //주문 전파 유물
        if (overKill > 0 && AbstractDungeon.player.hasRelic(MK_04_MagicStickHammerRelic.ID)) {
            AbstractRelic r = AbstractDungeon.player.getRelic(MK_04_MagicStickHammerRelic.ID);
            r.flash();
            this.addToBot(new RelicAboveCreatureAction(this.owner, r));
            this.addToBot(new ApplyPowerToRandomEnemyAction(AbstractDungeon.player, new MK_01_Majinai_Power(null, overKill), overKill, false, AbstractGameAction.AttackEffect.POISON));
        }

        //주문 유지 버프
        if(AbstractDungeon.player.hasPower(MK_18_SpicyNakjiKimchiJook_Power.POWER_ID)){
            return;
        }

        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new MK_02_MajinaiStrength_Power(AbstractDungeon.player)));
    }

    @Override
    public void onRemove() {
        super.onRemove();

        AbstractDungeon.effectList.add(
                new TextAboveCreatureEffect(
                        this.owner.hb.cX,
                        this.owner.hb.cY,
                        "풀려도 끝이 아니니까",
                        Settings.PURPLE_COLOR
                )
        );
    }

    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        //logger.info("stacked " + stackAmount + ampified);
        this.amount += (stackAmount + ampified);
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
