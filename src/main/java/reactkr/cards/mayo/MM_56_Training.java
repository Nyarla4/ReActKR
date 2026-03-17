package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.powers.mayo.MM_15_tempPower2;
import reactkr.powers.mayo.MM_16_TrainingPower;

import static reactkr.ModFile.makeID;

public class MM_56_Training extends AbstractEasyCard_Mayo {
    public final static String ID = makeID("Training");

    public MM_56_Training() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new MM_16_TrainingPower(p, magicNumber)));
    }

    @Override
    public void upp() {
        updateCost(-1);
    }
}
