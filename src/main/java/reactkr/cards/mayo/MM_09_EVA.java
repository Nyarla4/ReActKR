package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.actions.ModifiyMagicNumberAction;

import static reactkr.ModFile.makeID;

public class MM_09_EVA extends AbstractAimedCard {
    public final static String ID = makeID("EVA");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public MM_09_EVA() {
        super(ID, 0, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = damage = 6;
        baseSecondDamage = secondDamage = 8;
        baseBlock = block = 6;
        baseMagicNumber = magicNumber = 3;
    }

    @Override
    void normalUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, finalDamage)));
        if(magicNumber == 1){
            addToBot(new DamageAction(m, new DamageInfo(p, finalDamage)));
        }
        addToBot(new ModifiyMagicNumberAction(this.uuid, -1));
    }

    @Override
    void aimedUse(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void upp() {
        upgradeDamage(3);
        upgradeSecondDamage(3);
        upgradeMagicNumber(1);
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (magicNumber == 1) {
            exhaust = true;
        }
    }
}