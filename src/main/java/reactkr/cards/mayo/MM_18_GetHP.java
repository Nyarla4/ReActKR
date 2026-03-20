package reactkr.cards.mayo;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.powers.mayo.AbstractBulletPower;
import reactkr.powers.mayo.MM_B_03_HPBulletPower;

import static reactkr.ModFile.makeID;

public class MM_18_GetHP extends AbstractAimedCard {
    public final static String ID = makeID("GetHP");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public MM_18_GetHP() {
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
        AbstractBulletPower.load(p, new MM_B_03_HPBulletPower(p));
    }
}