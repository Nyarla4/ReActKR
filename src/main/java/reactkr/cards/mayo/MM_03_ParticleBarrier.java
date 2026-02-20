package reactkr.cards.mayo;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.IntangiblePower;
import reactkr.powers.AbstractEasyPower;
import reactkr.powers.mayo.MM_02_ParticleBarrierUltPower;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.applyToSelf;

public class MM_03_ParticleBarrier extends AbstractOverwatchCard {
    public final static String ID = makeID("ParticleBarrier");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public MM_03_ParticleBarrier() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        baseBlock = 16;
        baseMagicNumber = magicNumber = 1;
        baseSecondMagic = secondMagic = 1;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    void normalUse(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    void ultUse(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new IntangiblePower(p, 1));
        if (p.hasPower(MM_02_ParticleBarrierUltPower.POWER_ID)) {
            AbstractPower pow = p.getPower(MM_02_ParticleBarrierUltPower.POWER_ID);
            pow.amount += magicNumber;
            ((AbstractEasyPower) pow).amount2 += secondMagic;
            pow.updateDescription();
        }
        else{
            AbstractEasyPower pow = new MM_02_ParticleBarrierUltPower(p, magicNumber);
            pow.amount2 = secondMagic;
            applyToSelf(pow);
            pow.updateDescription();
        }
    }
}