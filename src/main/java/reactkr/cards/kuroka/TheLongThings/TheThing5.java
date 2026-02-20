package reactkr.cards.kuroka.TheLongThings;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.powers.kuroka.MK_01_Majinai_Power;

public class TheThing5 extends AbstractTheThingCard {

    public TheThing5() {
        super(5, new TheThing6(),CardType.SKILL, CardTarget.ENEMY);
    }

    @Override
    void persoanlEff(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new MK_01_Majinai_Power(m, 4)));
    }
}