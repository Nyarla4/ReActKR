package reactkr.cards.kuroka.TheLongThings;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TheThing4 extends AbstractTheThingCard {

    public TheThing4() {
        super(4, new TheThing5());
    }

    @Override
    void persoanlEff(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainEnergyAction(1));
    }
}