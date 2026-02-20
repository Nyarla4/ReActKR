package reactkr.cards.kuroka.TheLongThings;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import reactkr.powers.DelayedPower;

public class TheThing9 extends AbstractTheThingCard {

    public TheThing9() {
        super(9, new TheThing());
    }

    @Override
    void persoanlEff(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 2)));
        addToBot(new ApplyPowerAction(p, p, new DelayedPower(p, 1, new StrengthPower(p, -2))));
    }
}