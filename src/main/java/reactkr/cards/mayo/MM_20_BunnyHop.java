package reactkr.cards.mayo;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import reactkr.powers.mayo.MM_01_UltPower;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.applyToSelf;

public class MM_20_BunnyHop extends AbstractUltCard {
    public final static String ID = makeID("BunnyHop");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public MM_20_BunnyHop() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        this.isEthereal = true;
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }

    @Override
    void normalUse(AbstractPlayer p, AbstractMonster m) {
        if (ultCharged())
            return;
        applyToSelf(new BlurPower(p, 1));
    }

    @Override
    boolean ultUse(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new BlurPower(p, 3));
        return true;
    }
}