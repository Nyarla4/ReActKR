package reactkr.cards.mayo;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.powers.mayo.MM_03_EvasionPower;

import static reactkr.ModFile.makeID;

public class MM_35_EchoDrone extends AbstractEasyCard_Mayo {
    public final static String ID = makeID("EchoDrone");

    public MM_35_EchoDrone() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 12;
        baseSecondMagic = secondMagic = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new MM_03_EvasionPower(p, magicNumber)));
        addToBot(new StunMonsterAction(m, p, secondMagic));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(12);
    }
}
