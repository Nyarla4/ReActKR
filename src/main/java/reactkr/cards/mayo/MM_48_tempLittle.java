package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static reactkr.ModFile.makeID;

public class MM_48_tempLittle extends AbstractAimedCard {
    public final static String ID = makeID("tempLittle");

    public MM_48_tempLittle() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 1;
        baseSecondMagic = secondMagic = 1;
        useAim = true;
        useQuick = true;
    }

    @Override
    public void normalUse(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void aimedUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainEnergyAction(magicNumber));
    }

    @Override
    public void quickUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(secondMagic));
    }


    @Override
    protected boolean useBullet() {
        return false;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondMagic(1);
    }
}
