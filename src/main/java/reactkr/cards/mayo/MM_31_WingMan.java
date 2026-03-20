package reactkr.cards.mayo;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.powers.mayo.AbstractBulletPower;

import static reactkr.ModFile.makeID;

public class MM_31_WingMan extends AbstractAimedCard{
    public final static String ID = makeID("WingMan");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public MM_31_WingMan() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 6;
        baseSecondDamage = secondDamage = 8;
        baseMagicNumber = magicNumber = 5;
        this.exhaust = true;
    }

    @Override
    public void normalUse(AbstractPlayer p, AbstractMonster m) {
        int rand1 = AbstractDungeon.cardRandomRng.random(1, 100);
        if (rand1 <= 10) {
            finalDamage *= magicNumber;
        }
        addToBot(new DamageAction(m, new DamageInfo(p, finalDamage)));
    }

    @Override
    protected AbstractBulletPower.BulletTrigger bulletTrigger() {
        return AbstractBulletPower.BulletTrigger.ALWAYS;
    }

    @Override
    public void upp() {

    }
}
