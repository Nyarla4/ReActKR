package reactkr.cards.subcards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import reactkr.cards.AbstractEasyCard_Kuroka;

import static reactkr.ModFile.makeID;

public class MK_21_NumberSix extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("NumberSix");

    public MK_21_NumberSix() {
        super(ID, 0, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
        this.exhaust = true;
        baseDamage = damage = 6;
        tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
    }

    @Override
    public void upp() {
        this.selfRetain = true;
    }
}
