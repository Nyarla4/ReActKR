package reactkr.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CustomCallbackExhaustAction extends AbstractGameAction {
    private Consumer<List<AbstractCard>> callback; // 🌟 소멸된 카드들을 받을 구조

    public CustomCallbackExhaustAction(int amount, boolean anyNumber, Consumer<List<AbstractCard>> callback) {
        this.amount = amount;
        this.actionType = ActionType.EXHAUST;
        this.duration = Settings.ACTION_DUR_FAST;
        this.callback = callback;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (AbstractDungeon.player.hand.isEmpty()) {
                this.isDone = true;
                return;
            }
            AbstractDungeon.handCardSelectScreen.open("소멸할 카드를 선택하세요.", this.amount, false, false);
            this.tickDuration();
            return;
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            List<AbstractCard> exhaustedCards = new ArrayList<>();

            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                AbstractDungeon.player.hand.moveToExhaustPile(c);
                exhaustedCards.add(c);
            }

            if (this.callback != null) {
                this.callback.accept(exhaustedCards);
            }

            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }

        this.tickDuration();
    }
}