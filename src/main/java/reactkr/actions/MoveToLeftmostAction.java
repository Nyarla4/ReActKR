package reactkr.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class MoveToLeftmostAction extends AbstractGameAction {

    public MoveToLeftmostAction(int amount) {
        this.amount = amount; // 선택할 카드 장수
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        CardGroup hands = AbstractDungeon.player.hand;
        if (this.duration == Settings.ACTION_DUR_FAST) {

            if (hands.isEmpty()) {
                this.isDone = true;
                return;
            }

            int selectCount = Math.min(this.amount, hands.group.size());

            AbstractDungeon.handCardSelectScreen.open("가장 왼쪽으로 보낼 카드를 선택하세요.", selectCount, false, false);
            this.tickDuration();
            return;
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {

            int handIdx = 0;

            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                hands.group.add(handIdx, c);
                handIdx++;
            }

            // 엔진 초기화 및 화면 갱신
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();

            hands.refreshHandLayout();

            this.isDone = true;
        }

        this.tickDuration();
    }
}