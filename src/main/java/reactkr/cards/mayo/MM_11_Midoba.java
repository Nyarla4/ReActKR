package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.powers.DelayedPower;

import static reactkr.ModFile.makeID;

public class MM_11_Midoba extends AbstractEasyCard_Mayo {
    public final static String ID = makeID("Midoba");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public MM_11_Midoba() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 5;
        baseSecondMagic = secondMagic = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -magicNumber)));
        addToBot(new ApplyPowerAction(m, p, new DelayedPower(m, secondMagic, new StrengthPower(m, magicNumber))));
    }

    @Override
    public void upp() {
        upgradeSecondMagic(1);
    }
}