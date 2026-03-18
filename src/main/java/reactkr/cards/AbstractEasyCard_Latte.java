package reactkr.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.Latte;
import reactkr.cards.kuroka.AbstractWitchificateCard;
import reactkr.util.CardArtRoller;

public abstract class AbstractEasyCard_Latte extends AbstractEasyCard {

    private boolean needsArtRefresh = false;

    private static boolean isCloning = false;

    public AbstractEasyCard_Latte(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target) {
        this(cardID, cost, type, rarity, target, Latte.Enums.LATTE_COLOR);
    }

    public AbstractEasyCard_Latte(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, final CardColor color) {
        super(cardID, cost, type, rarity, target, color);
    }

    @Override
    public void update() {
        super.update();
        if (needsArtRefresh)
            CardArtRoller.computeCard(this);
    }

    protected abstract int getPreviewDescriptionIndex();

    protected abstract int getPreviewUpgradeDescriptionIndex();

    public void initializeSummaryPreview() {
        int idx = getPreviewDescriptionIndex();
        int uIdx = getPreviewUpgradeDescriptionIndex();
        if (isCloning || idx < 0) return; // 인덱스가 음수면 프리뷰 미생성

        // 2. 이제부터 복제를 시작한다고 문을 잠급니다.
        isCloning = true;

        try {
            // 3. 복사본 생성 (이 메서드 내부에서 new MK_06...이 호출되지만, isCloning이 true라 1번에서 걸러짐)
            AbstractCard preview = this.makeStatEquivalentCopy();

            preview.rawDescription = this.cardStrings.EXTENDED_DESCRIPTION[idx];

            if (upgraded && uIdx >= 0)
                preview.rawDescription = this.cardStrings.EXTENDED_DESCRIPTION[uIdx];

            preview.initializeDescription();

            this.cardsToPreview = preview;
        } finally {
            // 5. 작업이 끝났으므로(성공하든 에러가 나든) 다시 문을 열어줍니다.
            isCloning = false;
        }
    }

    @Override
    public void upgrade() {
        super.upgrade();
        initializeSummaryPreview();
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        initializeSummaryPreview();
    }
}
