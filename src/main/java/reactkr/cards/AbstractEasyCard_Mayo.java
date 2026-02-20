package reactkr.cards;

import reactkr.Mayo;
import reactkr.util.CardArtRoller;

public abstract class AbstractEasyCard_Mayo extends AbstractEasyCard {

    private boolean needsArtRefresh = false;

    public AbstractEasyCard_Mayo(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target) {
        this(cardID, cost, type, rarity, target, Mayo.Enums.MAYO_COLOR);
    }

    public AbstractEasyCard_Mayo(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, final CardColor color) {
        super(cardID, cost, type, rarity, target, color);
    }

    @Override
    public void update() {
        super.update();
        if (needsArtRefresh)
            CardArtRoller.computeCard(this);
    }

}
