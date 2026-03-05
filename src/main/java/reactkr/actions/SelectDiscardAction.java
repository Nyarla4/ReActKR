package reactkr.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SelectDiscardAction extends AbstractGameAction {
    private final AbstractPlayer p;

    public SelectDiscardAction(int amount) {
        this.p = AbstractDungeon.player;
        this.amount = amount; // 최대 선택 가능 장수 (2장)
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.DISCARD;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_XFAST) {
            if (p.hand.isEmpty()) {
                this.isDone = true;
                return;
            }

            /* open(String title, int amount, boolean anyNumber, boolean canPickZero)
               - anyNumber: true일 경우, 정해진 amount보다 적게 선택해도 '확인' 버튼이 활성화됩니다.
               - canPickZero: true일 경우, 한 장도 선택하지 않고 '확인'을 누를 수 있습니다.
            */
            AbstractDungeon.handCardSelectScreen.open("버릴 카드를 선택하세요 (최대 "+amount+"장).", this.amount, true, true);
            this.tickDuration();
            return;
        }

        // 사용자가 '확인(Done)' 버튼을 눌러 선택창이 닫히기 시작할 때의 흐름
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            if (!AbstractDungeon.handCardSelectScreen.selectedCards.isEmpty()) {
                for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                    // 선택된 카드들을 버림더미로 이동시키는 실행 과정
                    p.hand.moveToDiscardPile(c);
                    c.triggerOnManualDiscard();
                    GameActionManager.incrementDiscard(false);
                }
            }

            // 핸드 카드 선택 스크린 정리 및 리턴 처리
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            this.isDone = true;
        }

        this.tickDuration();
    }
}
