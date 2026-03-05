package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.powers.mayo.MM_08_OIIAPower;
import reactkr.powers.mayo.MM_09_ReloadPower;

import static reactkr.ModFile.makeID;

public class MM_34_Reload extends AbstractEasyCard_Mayo {
    public final static String ID = makeID("Reload");

    public MM_34_Reload() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new MM_09_ReloadPower(p, magicNumber)));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}
