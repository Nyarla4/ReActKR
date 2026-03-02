package reactkr.cards.latte;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import reactkr.cards.AbstractEasyCard_Latte;
import reactkr.util.CardArtRoller;

public abstract class AbstractAutoCard extends AbstractEasyCard_Latte {

    private boolean needsArtRefresh = false;
    protected boolean exhaustOnDraw;

    public AbstractAutoCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target) {
        this(cardID, cost, type, rarity, target, false);
    }

    public AbstractAutoCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, boolean exhaustOnDraw) {
        super(cardID, cost, type, rarity, target);
        this.exhaustOnDraw = exhaustOnDraw;
    }

    public void triggerWhenDrawn() {
        if (exhaustOnDraw) {
            this.addToBot(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
        }
        AutoAct();
    }

    public abstract void AutoAct();

    @Override
    public void update() {
        super.update();
        if (needsArtRefresh)
            CardArtRoller.computeCard(this);
    }
}
