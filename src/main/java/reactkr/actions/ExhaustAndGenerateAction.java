package reactkr.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.Madness;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;

import java.util.ArrayList;

public class ExhaustAndGenerateAction extends AbstractGameAction {
    private ArrayList<AbstractCard.CardColor> targetColors;
    private int exhaust;
    private int count;

    public ExhaustAndGenerateAction(ArrayList<AbstractCard.CardColor> colors, int exhaust, int count) {
        this.targetColors = colors;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.exhaust = exhaust;
        this.count = count;
    }

    @Override
    public void update() {
        // [흐름 1] 패에서 소멸시킬 카드 선택창 열기
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (AbstractDungeon.player.hand.isEmpty()) {
                this.isDone = true;
                return;
            }
            AbstractDungeon.handCardSelectScreen.open("소멸시킬 카드를 선택하세요.", exhaust, false, false);
            this.tickDuration();
            return;
        }

        // [흐름 2] 선택된 카드가 처리 대기 중인지 확인
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                // 1. [수행 과정] 선택한 카드 소멸 (시각 효과 포함)
                c.exhaust = true;
                AbstractDungeon.effectsQueue.add(new ExhaustCardEffect(c));
                c.triggerOnExhaust();
                AbstractDungeon.player.hand.moveToExhaustPile(c);

                for (int i = 0; i < this.count; i++) {
                    // 2. [구조적 생성] 앞서 만든 유틸리티로 무작위 카드 추출
                    AbstractCard newCard = getRandomCardFromColors(this.targetColors);

                    // 3. [흐름 반영] 생성된 카드를 패에 추가 (패가 꽉 찼을 경우 버림패로 이동하는 안전 구조 포함)
                    addToBot(new MakeTempCardInHandAction(newCard, 1));
                }
            }

            // 선택창 정리 및 핸드 레이아웃 갱신
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
        }

        this.tickDuration();
    }

    public AbstractCard getRandomCardFromColors(ArrayList<AbstractCard.CardColor> colors) {
        ArrayList<AbstractCard> cardPool = new ArrayList<>();

        // [구조] 게임 내 모든 카드를 순회하며 조건 필터링
        for (AbstractCard c : CardLibrary.getAllCards()) {
            // 지정된 색상 리스트에 포함됨 + 생성 제한(스페셜/상태/저주) 제외
            if (colors.contains(c.color) &&
                    c.rarity != AbstractCard.CardRarity.SPECIAL &&
                    c.type != AbstractCard.CardType.CURSE &&
                    c.type != AbstractCard.CardType.STATUS) {
                cardPool.add(c);
            }
        }

        // [흐름] 카드 풀에서 무작위 한 장 선택 (복사본 생성)
        if (!cardPool.isEmpty()) {
            return cardPool.get(AbstractDungeon.cardRandomRng.random(cardPool.size() - 1)).makeCopy();
        }
        return new Madness(); // 예외 대비 기본값
    }
}