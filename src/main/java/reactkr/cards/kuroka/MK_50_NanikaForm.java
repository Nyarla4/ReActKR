package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.powers.kuroka.MK_16_NanikaForm_Power;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.applyToSelf;

public class MK_50_NanikaForm extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("NanikaForm");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public MK_50_NanikaForm() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new MK_16_NanikaForm_Power(p,magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}