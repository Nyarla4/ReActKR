package reactkr.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.actions.TransformCardInHandAction;

import static reactkr.ModFile.makeID;

public class Kira_Song extends AbstractEasyCard {
    public final static String ID = makeID("Kira_Song");

    public Kira_Song() {
        this(false);
    }

    public Kira_Song(boolean isPreview) {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE, CardColor.COLORLESS);
        baseBlock = block = 6;
        selfRetain = true;
        if(!isPreview){
            cardsToPreview = new Kira_Chat(true);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void upp() {
        if (cardsToPreview != null) {
            cardsToPreview.upgrade();
        }
        upgradeBlock(4);
    }

    @Override
    public void onRetained() {
        AbstractCard target = new Kira_Chat();
        if(upgraded){
            target.upgrade();
        }
        addToTop(new TransformCardInHandAction(this, target.makeStatEquivalentCopy()));
    }
}
