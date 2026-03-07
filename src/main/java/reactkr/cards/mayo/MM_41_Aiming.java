package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.powers.mayo.MM_10_AimPower;

import static reactkr.ModFile.makeID;

public class MM_41_Aiming extends AbstractEasyCard_Mayo {
    public final static String ID = makeID("Aiming");

    public MM_41_Aiming() {
        super(ID, 1, CardType.POWER, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new MM_10_AimPower(p, magicNumber)));
    }

    @Override
    public void upp() {
        updateCost(-1);
    }
}
