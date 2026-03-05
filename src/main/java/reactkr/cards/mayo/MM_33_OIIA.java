package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.powers.mayo.MM_08_OIIAPower;

import static reactkr.ModFile.makeID;

public class MM_33_OIIA extends AbstractEasyCard_Mayo {
    public final static String ID = makeID("OIIA");

    public MM_33_OIIA() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new MM_08_OIIAPower(p, magicNumber)));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
        this.isInnate = true;
    }
}
