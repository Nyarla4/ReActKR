package reactkr.cards.kuroka.TheLongThings;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TheThing7 extends AbstractTheThingCard {

    public TheThing7() {
        super(7, new TheThing8());
    }

    @Override
    void persoanlEff(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(2));
    }
}