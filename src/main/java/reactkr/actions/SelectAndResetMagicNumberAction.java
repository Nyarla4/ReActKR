package reactkr.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import reactkr.cards.AbstractEasyCard;
import reactkr.cards.mayo.AbstractAimedCard;

public class SelectAndResetMagicNumberAction extends AbstractGameAction {
    private final CardGroup validCards;

    public SelectAndResetMagicNumberAction() {
        AbstractPlayer p = AbstractDungeon.player;
        CardGroup filteredGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        p.hand.group.stream()
                .filter(f -> f instanceof AbstractAimedCard && ((AbstractAimedCard) f).basicDepletion() > -1)
                .forEach(filteredGroup::addToTop);

        this.validCards = filteredGroup;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            // [흐름] 필터링된 그룹으로 창 열기
            AbstractDungeon.gridSelectScreen.open(validCards, 1, "고갈을 회복할 카드를 선택하세요.", false);
            this.tickDuration();
            return;
        }
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                if (c instanceof AbstractEasyCard) {
                    AbstractEasyCard ec = (AbstractEasyCard) c;
                    if (ec instanceof AbstractAimedCard) {
                        AbstractAimedCard ac = (AbstractAimedCard) ec;
                        ac.baseMagicNumber = ac.basicDepletion();
                    }
                }
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }
        this.isDone = true;
//
//        // [흐름 1] 선택창 열기
//        if (this.duration == Settings.ACTION_DUR_FAST) {
//            // 패에 카드가 없으면 즉시 종료
//            if (p.hand.isEmpty()) {
//                this.isDone = true;
//                return;
//            }
//            // 카드 선택창 오픈 (1장 선택)
//            AbstractDungeon.handCardSelectScreen.open("고갈을 회복할 카드를 선택하세요.", 1, false, false);
//            this.tickDuration();
//            return;
//        }
//
//        // [흐름 2] 선택된 카드 처리
//        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
//            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
//                if (c instanceof AbstractEasyCard) {
//                    AbstractEasyCard ec = (AbstractEasyCard) c;
//                    if (ec instanceof AbstractAimedCard) {
//                        AbstractAimedCard ac = (AbstractAimedCard) ec;
//                        ac.baseMagicNumber = ac.basicDepletion();
//                    }
//                }
//            }
//
//            // 선택창 정리 흐름
//            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
//            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
//            p.hand.refreshHandLayout();
//        }

        this.tickDuration();
    }
}
