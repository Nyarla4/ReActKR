package reactkr.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class CardChangeAction extends AbstractGameAction {
    private final AbstractCard targetCard;
    private final CardGroup group;

    public CardChangeAction(CardGroup cg, AbstractCard c, int amount) {
        this.group = cg;
        this.targetCard = c;
        this.amount = amount;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        // 방어 코드: 그룹이 비어있으면 흐름 즉시 종료
        if (this.group.isEmpty()) {
            this.isDone = true;
            return;
        }

        if (this.duration == Settings.ACTION_DUR_FAST) {

            int actualAmount = Math.min(this.amount, this.group.size());

            // 구조적 분기: 타겟 그룹이 손패인가, 그 외(덱/버린패)인가?
            if (this.group.type == CardGroup.CardGroupType.HAND) {
                AbstractDungeon.handCardSelectScreen.open("변환할 카드를 선택하세요.", actualAmount, false, false);
            } else {
                // Grid 화면 열기: (대상 그룹, 고를 장수, 텍스트, 취소불가, 제한없음, 카드강조안함, 역순정렬안함)
                AbstractDungeon.gridSelectScreen.open(this.group, actualAmount, "변환할 카드를 선택하세요.", false, false, false, false);
            }
            this.tickDuration();
            return;
        }

        // 2. [손패]에서 선택
        if (this.group.type == CardGroup.CardGroupType.HAND && !AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard selected : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                replaceCardStructurally(selected);
            }
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            AbstractDungeon.player.hand.refreshHandLayout();
            this.isDone = true;
            return;
        }

        // 3. [덱/버린패] Grid 창에서 선택
        if (this.group.type != CardGroup.CardGroupType.HAND &&
                !AbstractDungeon.isScreenUp &&
                !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            for (AbstractCard selected : AbstractDungeon.gridSelectScreen.selectedCards) {
                replaceCardStructurally(selected);
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            this.isDone = true;
            return;
        }

        this.tickDuration();
    }

    // 💡 내부 헬퍼 메서드: 카드를 '정확한 위치'에서 바꿔치기하는 구조적 조작
    private void replaceCardStructurally(AbstractCard oldCard) {
        AbstractCard newCard = this.targetCard.makeStatEquivalentCopy();

        // 기존 카드가 그룹 내에서 몇 번째(인덱스)에 있었는지 찾습니다.
        int index = this.group.group.indexOf(oldCard);

        if (index != -1) {
            // 구조적 교체: 해당 인덱스의 카드를 빼내고, 그 자리에 새 카드를 밀어 넣습니다.
            // 이렇게 하면 덱의 순서(시드)가 꼬이지 않고 완벽하게 변환만 일어납니다.
            this.group.group.set(index, newCard);
        } else {
            // 만약 못 찾았다면 맨 위에 넣는 예외 처리
            this.group.addToTop(newCard);
        }

        // 손패에서 변환된 경우, 카드가 화면 밖에서 날아오지 않고 '그 자리에서' 변환된 것처럼 보이도록 좌표 복사
        if (this.group.type == CardGroup.CardGroupType.HAND) {
            newCard.current_x = oldCard.current_x;
            newCard.current_y = oldCard.current_y;
            newCard.target_x = oldCard.target_x;
            newCard.target_y = oldCard.target_y;
            newCard.drawScale = oldCard.drawScale;
            newCard.targetDrawScale = oldCard.targetDrawScale;
            newCard.angle = oldCard.angle;
            newCard.targetAngle = oldCard.targetAngle;
            newCard.lighten(true); // 카드가 잠깐 밝게 빛나는 이펙트 부여
        }
    }
}