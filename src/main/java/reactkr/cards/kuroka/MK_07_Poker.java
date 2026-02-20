package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import reactkr.cards.AbstractEasyCard_Kuroka;

import static reactkr.ModFile.makeID;

public class MK_07_Poker extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("Poker");
    // intellij stuff skill, none, common, , , , , ,

    public MK_07_Poker() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 10;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if(m.intent == AbstractMonster.Intent.ATTACK ||
                m.intent == AbstractMonster.Intent.ATTACK_DEFEND ||
                m.intent == AbstractMonster.Intent.ATTACK_DEBUFF ||
                m.intent == AbstractMonster.Intent.ATTACK_BUFF
        ){
            dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        }
        else{
            addToBot(new ApplyPowerAction(p,p, new StrengthPower(p,magicNumber),magicNumber));
        }
    }

    public void upp() {
        upgradeDamage(4);
        upgradeMagicNumber(1);
    }
}
