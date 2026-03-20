package reactkr.cards.mayo;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.powers.mayo.AbstractBulletPower;
import reactkr.powers.mayo.MM_B_02_LightBulletPower;

import static reactkr.ModFile.makeID;

public class MM_06_GetLight extends AbstractEasyCard_Mayo {
    public final static String ID = makeID("GetLight");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public MM_06_GetLight() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
    }

    @Override
    public void upp() {
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractBulletPower.load(p, new MM_B_02_LightBulletPower(p));
    }
}