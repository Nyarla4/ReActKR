package reactkr.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.powers.ApplePower;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.applyToSelf;

public class ThrowTheApple extends AbstractEasyCard {
    public final static String ID = makeID("ThrowTheApple");

    public ThrowTheApple() {
        super(ID, 1, CardType.POWER, CardRarity.SPECIAL, CardTarget.SELF, CardColor.COLORLESS);
        baseMagicNumber = magicNumber = 6;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ApplePower(p, magicNumber));
    }

    @Override
    public void upp() {
    }

}