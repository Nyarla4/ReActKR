package reactkr.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class AL_11_SpinCardAction extends AbstractGameAction {
    private AbstractCard card;
    private int spinCount;

    public AL_11_SpinCardAction(AbstractCard card, float duration, int spinCount) {
        this.card = card;
        this.duration = duration;
        this.actionType = ActionType.WAIT;
        this.spinCount = spinCount;
    }

    @Override
    public void update() {
        // [수행 과정] 카드를 빙글빙글 돌리기 위해 목표 각도를 대폭 늘림
        // 한 바퀴가 360도이므로, 720, 1080 등으로 설정하면 여러 번 돕니다.
        if (this.duration == 0.5F) { // 시작 시점에 한 번만 설정
            this.card.targetAngle = 360.0f * spinCount;
        }

        this.tickDuration();

        // [흐름 마무리] 액션이 끝나면 카드는 자연스럽게 다음 목적지로 날아갑니다.
    }
}