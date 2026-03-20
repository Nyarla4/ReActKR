package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.powers.mayo.AbstractBulletPower;

import static reactkr.ModFile.makeID;

public class MM_09_EVA extends AbstractAimedCard {
    public final static String ID = makeID("EVA");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public MM_09_EVA() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 6;
        baseSecondDamage = secondDamage = 8;
        baseBlock = block = 6;
        baseMagicNumber = magicNumber = 6;
        usesDepletion = true;
        depletionMax = 6;
    }

    @Override
    public void normalUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, finalDamage)));
        if (magicNumber == 1) {
            addToBot(new DamageAction(m, new DamageInfo(p, finalDamage)));
        }
    }

    @Override
    public void upp() {
        upgradeDamage(3);
        upgradeSecondDamage(3);
        upgradeMagicNumber(-1);
        depletionMax = 5;
    }

    @Override
    protected AbstractBulletPower.BulletTrigger bulletTrigger() {
        return AbstractBulletPower.BulletTrigger.ALWAYS;
    }
}