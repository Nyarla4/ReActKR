package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Kuroka;

import static reactkr.ModFile.makeID;

public class MK_11_KurokaHitTwice extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("KurokaHitTwice");

    public MK_11_KurokaHitTwice() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 10;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
    }

    @Override
    public void upp() {
        upgradeDamage(4);
    }
}
