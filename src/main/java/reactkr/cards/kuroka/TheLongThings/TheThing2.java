package reactkr.cards.kuroka.TheLongThings;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.powers.kuroka.MK_03_DelusionFactor_Power;

import static reactkr.util.Wiz.applyToSelf;

public class TheThing2 extends AbstractTheThingCard {

    public TheThing2() {
        super(2, new TheThing3());
    }

    @Override
    void persoanlEff(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new MK_03_DelusionFactor_Power(p, 1));
    }
}