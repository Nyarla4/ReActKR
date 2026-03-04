package reactkr.cards.mayo;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.powers.mayo.MM_03_EvasionPower;
import reactkr.powers.mayo.MM_04_CounterPower;
import reactkr.powers.mayo.MM_05_ConfidencePower;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.applyToSelf;

public class MM_25_Confidence extends AbstractEasyCard_Mayo {
    public final static String ID = makeID("Confidence");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public MM_25_Confidence() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 12;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new MM_03_EvasionPower(p, magicNumber));
        applyToSelf(new MM_05_ConfidencePower(p));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(13);
    }
}