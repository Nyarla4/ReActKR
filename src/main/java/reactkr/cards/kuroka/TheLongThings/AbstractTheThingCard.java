package reactkr.cards.kuroka.TheLongThings;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Kuroka;

import static reactkr.ModFile.makeID;

public abstract class AbstractTheThingCard extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("TheThing");

    public AbstractTheThingCard(int index, AbstractCard nextCard) {
        this(index, nextCard, CardType.SKILL, CardTarget.NONE);
    }

    public AbstractTheThingCard(int index, AbstractCard nextCard, CardType type, CardTarget target) {
        super(ID + index, 0, type, CardRarity.SPECIAL, target);
        this.exhaust = true;
        cardsToPreview = nextCard;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new MakeTempCardInDrawPileAction(this.cardsToPreview.makeStatEquivalentCopy(), 1, true, true));
        if(upgraded){
            this.addToBot(new DrawCardAction(p, 1));
        }
        persoanlEff(p,m);
    }

    abstract void persoanlEff(AbstractPlayer p, AbstractMonster m);

    public void upp() {
        cardsToPreview.upgrade();
    }
}