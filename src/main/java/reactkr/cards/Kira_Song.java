package reactkr.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.actions.TransformCardInHandAction;

import static reactkr.ModFile.makeID;

public class Kira_Song extends AbstractPreviewCard {
    public final static String ID = makeID("Kira_Song");

    public Kira_Song() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE, CardColor.COLORLESS);
        baseBlock = block = 6;
        selfRetain = true;
    }

    @Override
    public void update(){
        super.update();
        if(this.cardsToPreview == null){
            initializePreview(new Kira_Chat());
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void upp() {
        this.cardsToPreview = null;
        upgradeBlock(4);
    }

    @Override
    public void onRetained() {
        addToTop(new TransformCardInHandAction(this,new Kira_Chat()));
    }
}
