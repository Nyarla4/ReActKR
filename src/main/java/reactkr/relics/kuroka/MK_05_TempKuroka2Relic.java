package reactkr.relics.kuroka;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerToRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import reactkr.Kuroka;
import reactkr.powers.kuroka.MK_01_Majinai_Power;
import reactkr.relics.AbstractEasyRelic;

import static reactkr.ModFile.makeID;

public class MK_05_TempKuroka2Relic extends AbstractEasyRelic {
    public static final String ID = makeID("TempKuroka2Relic");

    public MK_05_TempKuroka2Relic() {
        super(ID, RelicTier.UNCOMMON, LandingSound.FLAT, Kuroka.Enums.KUROKA_COLOR);
    }

    public void onExhaust(AbstractCard card) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new ApplyPowerToRandomEnemyAction(AbstractDungeon.player, new MK_01_Majinai_Power(null, 7), 7, false, AbstractGameAction.AttackEffect.POISON));
        }

    }

    @Override
    public void initializeTips(){
        super.initializeTips();

        String keyword = makeID("주문");
        this.tips.add(new PowerTip(BaseMod.getKeywordProper(keyword),
                BaseMod.getKeywordDescription(keyword)));
    }
}
