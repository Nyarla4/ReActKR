package reactkr.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;

public abstract class AbstractPreviewCard extends AbstractEasyCard {
    // [구조] 복제 상태 플래그
    private static boolean isCloning = false;

    public AbstractPreviewCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color) {
        super(cardID, cost, type, rarity, target, color);
    }

    // [흐름] 프리뷰 카드를 안전하게 생성하는 공통 로직
    public void initializePreview(AbstractCard nextCard) {
        if (isCloning || nextCard == null) return; // 이미 복제 중이면 흐름 중단

        isCloning = true;
        try {
            // 현재 카드의 강화 상태를 계승한 복사본 생성
            if (this.upgraded && !nextCard.upgraded) {
                nextCard.upgrade();
            }
            this.cardsToPreview = nextCard;
        } finally {
            isCloning = false; // 작업 완료 후 락 해제
        }
    }
}