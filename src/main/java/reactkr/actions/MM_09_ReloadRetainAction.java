package reactkr.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static com.megacrit.cardcrawl.core.Settings.ACTION_DUR_FAST;

public class MM_09_ReloadRetainAction extends AbstractGameAction {
    //비 공격 카드들 임시 저장
    private CardGroup cannotRetain = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

    public MM_09_ReloadRetainAction(int amount) {
        this.amount = amount;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {

            // 1. 패가 비었으면 종료
            if (AbstractDungeon.player.hand.isEmpty()) {
                this.isDone = true;
                return;
            }

            // 2. 공격(ATTACK)이 아닌 카드를 임시 그룹으로 이동
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c.type != AbstractCard.CardType.ATTACK) {
                    this.cannotRetain.addToTop(c);
                }
            }

            // 2-2. 실제 제거
            AbstractDungeon.player.hand.group.removeAll(this.cannotRetain.group);

            // 3. 패에 공격 없는 경우 복구
            if (AbstractDungeon.player.hand.isEmpty()) {
                returnCardsToHand(); // 치워둔 카드 원상복구
                this.isDone = true;
                return;
            }

            // 4. 남은 공격 카드가 보존할 수 있는 장수(amount) 이하라면 묻지 않고 전부 보존
            if (AbstractDungeon.player.hand.size() <= this.amount) {
                for (AbstractCard c : AbstractDungeon.player.hand.group) {
                    c.retain = true;
                }
                returnCardsToHand();
                this.isDone = true;
                return;
            }

            // 5. 플레이어에게 선택 화면 제공 흐름 (최대 amount장, 안 고를 수도 있음)
            AbstractDungeon.handCardSelectScreen.open(
                    "보존할 공격 카드를 선택하세요.",
                    this.amount,
                    false, // 아무 수나? (false)
                    true,  // 0장 선택 가능? (true - 안 고를 수도 있으니)
                    false, false,
                    true   // 최대 amount장까지? (true - upTo 옵션)
            );
            this.tickDuration();
            return;
        }

        // 6. 플레이어가 카드 선택을 마치고 확인 버튼을 누른 후
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            // 선택된 공격 카드들에게 보존(retain) 딱지를 붙이고 다시 손패로 넣습니다.
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                c.retain = true;
                AbstractDungeon.player.hand.addToTop(c);
            }

            returnCardsToHand(); // 그 외 스킬 복구

            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            this.isDone = true;
        }
        this.tickDuration();
    }

    // 복구 함수
    private void returnCardsToHand() {
        for (AbstractCard c : this.cannotRetain.group) {
            AbstractDungeon.player.hand.addToTop(c);
        }
        AbstractDungeon.player.hand.refreshHandLayout(); // 카드 정렬 갱신
    }
}
