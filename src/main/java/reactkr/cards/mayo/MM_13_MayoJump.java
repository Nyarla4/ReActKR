package reactkr.cards.mayo;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.actions.EasyModalChoiceAction;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.cards.EasyModalChoiceCard;
import reactkr.cards.subcards.MM_12_Five;
import reactkr.cards.subcards.MM_12_Seven;
import reactkr.util.ProAudio;

import java.util.ArrayList;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.*;

public class MM_13_MayoJump extends AbstractAimedCard {
    public final static String ID = makeID("MayoJump");
    // intellij stuff skill, self, uncommon, , , , , ,

    public MM_13_MayoJump() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 1;
        baseSecondMagic = secondMagic = 3;
        useAim = true;
    }

    @Override
    public void normalUse(AbstractPlayer p, AbstractMonster m) {
        if(!isAimed()){
            addToBot(new DrawCardAction(magicNumber));
        }
    }

    @Override
    public void aimedUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(secondMagic));
    }

    @Override
    public int basicDepletion() {
        return -1;
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }

    @Override
    protected boolean useBullet() {
        return false;
    }
}
