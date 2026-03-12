package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.powers.mayo.MM_03_EvasionPower;
import reactkr.powers.mayo.MM_12_ExcitedPower;

import static reactkr.ModFile.makeID;

public class MM_46_Excited extends AbstractEasyCard_Mayo {
    public final static String ID = makeID("Excited");

    public MM_46_Excited() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 5;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new MM_03_EvasionPower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new MM_12_ExcitedPower(p, magicNumber)));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }
}
