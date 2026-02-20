package reactkr.cards.subcards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import reactkr.cards.AbstractEasyCard_Kuroka;

import static reactkr.ModFile.makeID;

public class MK_21_NumberFour extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("NumberFour");

    public MK_21_NumberFour() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE, CardColor.COLORLESS);
        this.exhaust = true;
        baseBlock = block = 4;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void upp() {
        this.selfRetain = true;
    }
}
