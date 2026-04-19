package reactkr.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.actions.TransformCardInHandAction;

import static reactkr.ModFile.makeID;

public class MouthTen extends AbstractEasyCard {
    public final static String ID = makeID("mouthten");

    public MouthTen() {
        this(false);
    }

    public MouthTen(boolean isPreview) {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE, CardColor.COLORLESS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void upp() {
    }
}
