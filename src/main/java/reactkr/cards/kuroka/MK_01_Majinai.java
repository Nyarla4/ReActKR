package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.powers.kuroka.MK_01_Majinai_Power;

import static reactkr.ModFile.makeID;

public class MK_01_Majinai extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("Majinai");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public MK_01_Majinai() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 32;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new MK_01_Majinai_Power(m,magicNumber)));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(12);
    }
}