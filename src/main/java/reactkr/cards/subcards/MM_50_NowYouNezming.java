package reactkr.cards.subcards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.cards.mayo.AbstractAimedCard;
import reactkr.powers.mayo.MM_03_EvasionPower;

import static reactkr.ModFile.makeID;

public class MM_50_NowYouNezming extends AbstractAimedCard {
    public final static String ID = makeID("NowYouNezming");

    public MM_50_NowYouNezming() {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        baseBlock = block = 6;
        baseMagicNumber = magicNumber = 2;
        baseSecondMagic = secondMagic = 1;
        usesDepletion = true;
        depletionMax = 2;
    }

    @Override
    public void normalUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        addToBot(new DrawCardAction(secondMagic));
    }

    @Override
    protected boolean useBullet() {
        return false;
    }

    @Override
    public void upp() {
        upgradeBlock(2);
    }
}
