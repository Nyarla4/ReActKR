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
                .filter(f -> f instanceof AbstractAimedCard
                        && ((AbstractAimedCard) f).usesDepletion)
                .forEach(filteredGroup::addToTop);

        this.validCards = filteredGroup;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractDungeon.gridSelectScreen.open(validCards, 1, "고갈을 회복할 카드를 선택하세요.", false);
            this.tickDuration();
            return;
        }
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                if (c instanceof AbstractAimedCard) {
                    AbstractAimedCard ac = (AbstractAimedCard) c;
                    ac.baseMagicNumber = ac.depletionMax; // 탄창 리셋
                    ac.magicNumber     = ac.depletionMax; // UI 즉시 반영
                }
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }
        this.isDone = true; // ← 대기 후 종료
    }
}