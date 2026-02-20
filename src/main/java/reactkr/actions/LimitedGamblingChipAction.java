package reactkr.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LimitedGamblingChipAction extends AbstractGameAction {
    private AbstractPlayer p;
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private int maxAmount;

    private static final Logger logger = LogManager.getLogger(LimitedGamblingChipAction.class.getName());

    public LimitedGamblingChipAction(AbstractCreature source) {
        this.setValues(AbstractDungeon.player, source, -1);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.maxAmount = 99;
    }

    public LimitedGamblingChipAction(AbstractCreature source, int maxAmount) {
        this.setValues(AbstractDungeon.player, source, -1);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.maxAmount = maxAmount;
    }

    public void update() {
        if (this.duration == 0.5F) {
            AbstractDungeon.handCardSelectScreen.open(TEXT[0] + maxAmount + TEXT[1], maxAmount, true, true);

            this.addToBot(new WaitAction(0.25F));
            this.tickDuration();
        } else {
            if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
                if (!AbstractDungeon.handCardSelectScreen.selectedCards.group.isEmpty()) {
                    this.addToTop(new DrawCardAction(this.p, AbstractDungeon.handCardSelectScreen.selectedCards.group.size()));

                    for(AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                        AbstractDungeon.player.hand.moveToDiscardPile(c);
                        GameActionManager.incrementDiscard(false);
                        c.triggerOnManualDiscard();
                    }
                }

                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            }

            this.tickDuration();
        }
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("LimitedGamblingChipAction");
        TEXT = uiStrings.TEXT;
    }
}
