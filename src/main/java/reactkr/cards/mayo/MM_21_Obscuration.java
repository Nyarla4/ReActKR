package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static reactkr.ModFile.makeID;

public class MM_21_Obscuration extends AbstractAimedCard {
    public final static String ID = makeID("Obscuration");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public MM_21_Obscuration() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseDamage = damage = 6;
        baseSecondDamage = secondDamage = 10;
        baseMagicNumber = magicNumber = 3;
        useAim = true;
        useQuick = true;
    }

    @Override
    public void normalUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, finalDamage));
//        if(magicNumber == 1){
//            addToBot(new DamageAction(m, new DamageInfo(p, finalDamage)));
//        }
    }

    @Override
    public void aimedUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, magicNumber));
    }

    @Override
    public void quickUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeDamage(-2);
        upgradeSecondDamage(5);
        upgradeMagicNumber(1);
    }
}