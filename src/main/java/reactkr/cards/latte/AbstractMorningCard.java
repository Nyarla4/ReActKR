package reactkr.cards.latte;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Latte;
import reactkr.relics.latte.AL_01_TtediRelic;

public abstract class AbstractMorningCard extends AbstractEasyCard_Latte {

    public boolean isFirst = false;

    public AbstractMorningCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.isFirst = canReceiveFirstBonus();

        morningUse(p, m);
    }

    public abstract void morningUse(AbstractPlayer p, AbstractMonster m);

    @Override
    public void triggerOnGlowCheck() {
        super.triggerOnGlowCheck();

        if (canReceiveFirstBonus()) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    private boolean canReceiveFirstBonus() {
        int threshold = 1; // 기본은 첫 번째 카드

        // 특정 유물이 있다면 기준을 2장으로 상향
        if (AbstractDungeon.player.hasRelic(AL_01_TtediRelic.ID)) {
            threshold = 2;
        }

    /* 핵심 포인트:
       1. triggerOnGlowCheck 시점: 아직 카드를 내기 전이라 리스트에 이 카드가 없음.
          따라서 리스트 크기가 threshold 미만이면 OK.
       2. use 시점: 이미 리스트에 이 카드가 포함됨.
          따라서 리스트 크기가 threshold 이하면 OK.
    */
        int currentCount = AbstractDungeon.actionManager.cardsPlayedThisTurn.size();

        // 이미 사용된 카드 목록에 '이 카드'가 포함되어 있다면 (use 시점)
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.contains(this)) {
            return currentCount <= threshold;
        } else {
            // 아직 사용 전이라면 (Glow 체크 시점)
            return currentCount < threshold;
        }
    }
}
