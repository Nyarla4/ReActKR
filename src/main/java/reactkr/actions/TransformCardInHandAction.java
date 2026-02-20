package reactkr.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class TransformCardInHandAction extends AbstractGameAction {
    private AbstractCard oldCard;
    private AbstractCard newCard;

    public TransformCardInHandAction(AbstractCard oldCard, AbstractCard newCard) {
        this.oldCard = oldCard;
        this.newCard = newCard;
    }

    @Override
    public void update() {
        if (AbstractDungeon.player.hand.contains(oldCard)) {
            int index = AbstractDungeon.player.hand.group.indexOf(oldCard);

            // [구조 계승] 강화 상태 및 현재 코스트 등 상태 유지
            if (oldCard.upgraded) newCard.upgrade();
            newCard.current_x = oldCard.current_x;
            newCard.current_y = oldCard.current_y;
            newCard.target_x = oldCard.target_x;
            newCard.target_y = oldCard.target_y;

            // [흐름] 패의 객체 교체
            AbstractDungeon.player.hand.group.set(index, newCard);
            newCard.flash();
        }
        this.isDone = true;
    }
}
