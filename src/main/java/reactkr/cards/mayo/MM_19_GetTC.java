package reactkr.cards.mayo;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.powers.mayo.AbstractBulletPower;
import reactkr.powers.mayo.MM_B_04_TCBulletPower;

import static reactkr.ModFile.makeID;

public class MM_19_GetTC extends AbstractAimedCard {
    public final static String ID = makeID("GetTC");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public MM_19_GetTC() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        usesDepletion = true;
        depletionMax = 2;
    }

    @Override
    public void upp() {
    }

    @Override
    public void normalUse(AbstractPlayer p, AbstractMonster m) {
        AbstractBulletPower.load(p, new MM_B_04_TCBulletPower(p));
    }
}