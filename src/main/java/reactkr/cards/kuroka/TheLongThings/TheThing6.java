package reactkr.cards.kuroka.TheLongThings;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import reactkr.powers.DelayedPower;

public class TheThing6 extends AbstractTheThingCard {

    public TheThing6() {
        super(6, new TheThing7());
    }

    @Override
    void persoanlEff(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, 2)));
        addToBot(new ApplyPowerAction(p, p, new DelayedPower(p, 1, new DexterityPower(p, -2))));
    }
}