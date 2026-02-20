package reactkr.cards.kuroka.TheLongThings;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TheThing1 extends AbstractTheThingCard {

    public TheThing1() {
        super(1, new TheThing2(), CardType.ATTACK, CardTarget.ENEMY);
        baseDamage = damage = 4;
    }

    @Override
    void persoanlEff(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
    }
}