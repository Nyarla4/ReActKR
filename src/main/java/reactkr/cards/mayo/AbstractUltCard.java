package reactkr.cards.mayo;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Mayo;

public abstract class AbstractUltCard extends AbstractEasyCard_Mayo implements IUltCard {

    public AbstractUltCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        normalUse(p, m);           // 일반 사용
        this.handleUltFlow(p, m);  // IUltCard에 정의된 궁극기 처리
    }

    abstract void normalUse(AbstractPlayer p, AbstractMonster m);

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = ultCharged() ?
                AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy() :
                AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
    }
}