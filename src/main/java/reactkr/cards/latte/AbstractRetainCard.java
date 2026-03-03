package reactkr.cards.latte;

import reactkr.Latte;
import reactkr.cards.AbstractEasyCard_Latte;
import reactkr.util.CardArtRoller;

public abstract class AbstractRetainCard extends AbstractEasyCard_Latte {

    private boolean needsArtRefresh = false;

    public AbstractRetainCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target) {
        this(cardID, cost, type, rarity, target, Latte.Enums.LATTE_COLOR);
    }

    public AbstractRetainCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, final CardColor color) {
        super(cardID, cost, type, rarity, target, color);
        this.selfRetain = true;
    }

    @Override
    public void onRetained() {
        ContinuousAct();
    }

    public abstract void ContinuousAct();

    @Override
    public void update() {
        super.update();
        if (needsArtRefresh)
            CardArtRoller.computeCard(this);
    }
}
