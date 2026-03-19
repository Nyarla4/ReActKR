package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static reactkr.ModFile.makeID;

public class MM_10_VK47 extends AbstractAimedCard {
    public final static String ID = makeID("VK47");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public MM_10_VK47() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 5;
        baseSecondDamage = secondDamage = 9;
        baseMagicNumber = magicNumber = 3;
        usesDepletion = true;
        depletionMax = 3;
    }

    @Override
    public void normalUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, finalDamage)));
    }

    @Override
    public void upp() {
        upgradeDamage(-2);
        upgradeSecondDamage(5);
        upgradeMagicNumber(1);
        depletionMax = 4;
    }

    @Override
    protected boolean useBullet() {
        return true;
    }
}