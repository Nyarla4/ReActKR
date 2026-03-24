package reactkr.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.Madness;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;

import java.util.ArrayList;

public class DiscoveryColorAction extends AbstractGameAction {
    private ArrayList<AbstractCard.CardColor> targetColor;
    private boolean retrieveCard = false;

    // 파라미터가 List에서 단일 Color로 변경되었고, 가져올 매수(amount)를 받습니다.
    public DiscoveryColorAction(ArrayList<AbstractCard.CardColor> color, int amount) {
        this.targetColor = color;
        this.amount = amount;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        // 🌟 [흐름 1] 지정된 색상의 카드 3장을 화면에 띄우기
        if (this.duration == Settings.ACTION_DUR_FAST) {
            ArrayList<AbstractCard> cardPool = getFilteredCardPool();
            ArrayList<AbstractCard> choices = new ArrayList<>();

            // 중복 없이 무작위로 3장 뽑기
            while (choices.size() < 3 && !cardPool.isEmpty()) {
                AbstractCard c = cardPool.remove(AbstractDungeon.cardRandomRng.random(cardPool.size() - 1));
                choices.add(c.makeCopy());
            }

            // 조건에 맞는 카드가 아예 없을 경우의 예외 처리 (구조적 안전망)
            if (choices.isEmpty()) {
                choices.add(new Madness());
            }

            // 슬더스의 "발견(Discover)" UI 창을 엽니다. (false = 스킵 불가)
            AbstractDungeon.cardRewardScreen.customCombatOpen(choices, "패로 가져올 카드를 선택하세요.", false);
            this.tickDuration();
            return;
        }

        // 🌟 [흐름 2] 플레이어가 화면에서 1장을 선택하고 확인을 눌렀을 때
        if (!this.retrieveCard) {
            if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
                // 선택한 카드의 복사본 생성
                AbstractCard chosenCard = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();

                // (선택 사항) 가져온 카드의 코스트를 이번 턴에만 0으로 만들고 싶다면 아래 주석 해제
                // chosenCard.setCostForTurn(0);

                // 💡 [결과 반영] 선택된 카드를 지정된 수량(amount)만큼 패에 추가합니다!
                AbstractDungeon.actionManager.addToTop(new MakeTempCardInHandAction(chosenCard, this.amount));

                // 엔진 캐시 정리
                AbstractDungeon.cardRewardScreen.discoveryCard = null;
            }
            this.retrieveCard = true;
        }

        this.tickDuration();
    }

    // 💡 [구조] 조건에 맞는 카드들만 모아둔 풀(Pool)을 반환하는 헬퍼 메서드
    private ArrayList<AbstractCard> getFilteredCardPool() {
        ArrayList<AbstractCard> pool = new ArrayList<>();

        for (AbstractCard c : CardLibrary.getAllCards()) {
            // targetColor와 일치하는 카드만 수집 (상태 이상, 저주 등 제외)
            if (this.targetColor.contains(c.color) &&
                    c.rarity != AbstractCard.CardRarity.SPECIAL &&
                    c.type != AbstractCard.CardType.CURSE &&
                    c.type != AbstractCard.CardType.STATUS) {
                pool.add(c);
            }
        }
        return pool;
    }
}