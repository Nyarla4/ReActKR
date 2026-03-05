package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.cards.subcards.MM_32_Dongkk;

import static reactkr.ModFile.makeID;

public class MM_32_Babkk extends AbstractEasyCard_Mayo {
    public final static String ID = makeID("Babkk");

    public MM_32_Babkk() {
        this(false);
    }

    public MM_32_Babkk(boolean isPreview) {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = block = 6;
        if (!isPreview) {
            cardsToPreview = new MM_32_Dongkk(true);
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        blck();
        AbstractCard target = new MM_32_Dongkk();
        if (upgraded)
            target.upgrade();
        addToBot(new MakeTempCardInDrawPileAction(target.makeStatEquivalentCopy(), 1, true, true));
        //addToBot(new MakeTempCardInHandAction(target.makeStatEquivalentCopy(), 1));
    }

    @Override
    public void upp() {
        if (cardsToPreview != null) {
            cardsToPreview.upgrade();
        }
        upgradeBlock(4);
    }

    @Override
    public AbstractCard makeCopy() {
        return new MM_32_Babkk();
    }
}
