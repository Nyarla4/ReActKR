package reactkr.cards.subcards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Mayo;

public abstract class MM_08_AbstractRotten extends AbstractEasyCard_Mayo {
    public MM_08_AbstractRotten(final String cardID, final int cost) {
        super(cardID, cost, CardType.STATUS, CardRarity.SPECIAL, CardTarget.NONE, CardColor.COLORLESS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
    }

    @Override
    public void upp() {
    }
}
