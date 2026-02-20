package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;
import reactkr.cards.AbstractEasyCard_Kuroka;

import static reactkr.ModFile.makeID;

public class MK_37_Cutie extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("Cutie");
    // intellij stuff skill, self, common, , , , , 1, 2

    public MK_37_Cutie() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 5;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p, new RegenPower(p,magicNumber),magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }

}
