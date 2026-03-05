package reactkr.cards.subcards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.cards.mayo.MM_32_Babkk;

import static reactkr.ModFile.makeID;

public class MM_32_Chongkk extends AbstractEasyCard_Mayo {
    public final static String ID = makeID("Chongkk");

    public MM_32_Chongkk() {
        this(false);
    }

    public MM_32_Chongkk(boolean isPreview) {
        super(ID, 1, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        baseDamage = damage = 6;
        if (!isPreview) {
            cardsToPreview = new MM_32_Babkk(true);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        AbstractCard target = new MM_32_Babkk();
        if(upgraded)
            target.upgrade();
        addToBot(new MakeTempCardInDrawPileAction(target.makeStatEquivalentCopy(), 1, true, true));
        //addToBot(new MakeTempCardInHandAction(target.makeStatEquivalentCopy(), 1));
    }

    @Override
    public void upp() {
        if (cardsToPreview != null) {
            cardsToPreview.upgrade();
        }
        upgradeDamage(4);
    }

    @Override
    public AbstractCard makeCopy() {
        return new MM_32_Chongkk();
    }
}
