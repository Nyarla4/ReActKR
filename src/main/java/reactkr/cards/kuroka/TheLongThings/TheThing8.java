package reactkr.cards.kuroka.TheLongThings;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TheThing8 extends AbstractTheThingCard {

    public TheThing8() {
        super(8, new TheThing9());
        baseBlock = 3;
    }

    @Override
    void persoanlEff(AbstractPlayer p, AbstractMonster m) {
        blck();
    }
}