package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.cards.kuroka.TheLongThings.TheThing1;

import static reactkr.ModFile.makeID;

public class MK_04_TheLongThing extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("TheLongThing");
    // intellij stuff skill, self, rare, , , , , , 

    public MK_04_TheLongThing() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        this.exhaust = true;
        cardsToPreview = new TheThing1();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new MakeTempCardInDrawPileAction(this.cardsToPreview.makeStatEquivalentCopy(), 1, true, true));
        this.addToBot(new DrawCardAction(p,1));
    }

    public void upp() {
        this.isInnate = true;
        this.cardsToPreview.upgrade();
    }
}