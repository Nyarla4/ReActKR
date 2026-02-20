package reactkr.cards.subcards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import reactkr.cards.AbstractEasyCard_Kuroka;

import static reactkr.ModFile.makeID;

public class MK_20_ColorRed extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("ColorRed");

    public MK_20_ColorRed() {
        super(ID, 0, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
        this.exhaust = true;
        baseDamage = damage = 4;
        baseMagicNumber = magicNumber = 4;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, magicNumber, false)));
    }

    @Override
    public void upp() {
        upgradeDamage(2);
        this.selfRetain = true;
    }
}
