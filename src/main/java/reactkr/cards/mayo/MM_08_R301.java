package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.powers.mayo.AbstractBulletPower;

import static reactkr.ModFile.makeID;

public class MM_08_R301 extends AbstractAimedCard {

    public final static String ID = makeID("R301");

    public MM_08_R301() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 6;
        baseSecondDamage = secondDamage = 8;
        baseBlock = block = 6;
        baseMagicNumber = magicNumber = 2;
        useAim = true;
        usesDepletion = true;
        depletionMax = 2;
    }

    @Override
    public void normalUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, finalDamage, this.damageTypeForTurn)));
    }

    @Override
    public void aimedUse(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void upp() {
        upgradeDamage(3);
        upgradeSecondDamage(3);
        upgradeMagicNumber(1);
        depletionMax = 3;
    }

    @Override
    protected AbstractBulletPower.BulletTrigger bulletTrigger() {
        return AbstractBulletPower.BulletTrigger.ALWAYS;
    }
}