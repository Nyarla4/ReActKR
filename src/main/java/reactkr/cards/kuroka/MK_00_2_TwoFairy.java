package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.powers.kuroka.MK_01_Majinai_Power;

import static reactkr.ModFile.makeID;

public class MK_00_2_TwoFairy extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("TwoFairy");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public MK_00_2_TwoFairy() {
        super(ID, 0, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = damage = 2;
        baseMagicNumber = magicNumber = 2;
        baseSecondMagic = secondMagic = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < secondMagic; i++) {
            dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
            addToBot(new ApplyPowerAction(m, p, new MK_01_Majinai_Power(m, magicNumber)));
            if (upgraded) {
                addToBot(new ApplyPowerAction(m, p, new WeakPower(m, magicNumber, false)));
            }
        }
    }

    @Override
    public void upp() {

    }
}