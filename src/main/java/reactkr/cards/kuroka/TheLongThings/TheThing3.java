package reactkr.cards.kuroka.TheLongThings;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.actions.AddRorokaHPAction;

public class TheThing3 extends AbstractTheThingCard {

    public TheThing3() {
        super(3, new TheThing4());
    }

    @Override
    void persoanlEff(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AddRorokaHPAction(p, p, 4));
    }
}