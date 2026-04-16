package reactkr.cards.subcards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import reactkr.actions.TransformCardInHandAction;
import reactkr.cards.AbstractEasyCard_Mayo;

public abstract class MM_08_AbstractCooking extends AbstractEasyCard_Mayo {
    public MM_08_AbstractCooking(final String cardID, final int cost, final CardType type, int block, int magic, AbstractCard next) {
        super(cardID, cost, type, CardRarity.SPECIAL, CardTarget.NONE, CardColor.COLORLESS);
        this.block = baseBlock = block;
        this.magicNumber = baseMagicNumber = magic;
        this.selfRetain = true;
        this.exhaust = true;
        cardsToPreview = next;
    }

    public MM_08_AbstractCooking(final String cardID, final int cost, final CardType type, int block, AbstractCard next) {
        this(cardID,cost,type,block,0,next);
    }

    @Override
    public void onRetained() {
        AbstractCard target = cardsToPreview;
        if (target != null) {
            if(upgraded && target.type != CardType.STATUS){
                target.upgrade();
            }
            addToTop(new TransformCardInHandAction(this, target.makeStatEquivalentCopy()));
        }
    }

    @Override
    public void upp() {
        if (cardsToPreview != null) {
            cardsToPreview.upgrade();
        }
        upgradeBlock(upgradeBlockAmount());
    }

    protected abstract int upgradeBlockAmount();
}
