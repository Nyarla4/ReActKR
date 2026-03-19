package reactkr.cards.mayo;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.TextAboveCreatureEffect;
import reactkr.actions.EasyModalChoiceAction;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.cards.EasyModalChoiceCard;
import reactkr.cards.subcards.MM_52_MayoKuroka;
import reactkr.cards.subcards.MM_52_MayoSiah;
import reactkr.cards.subcards.MM_52_MayoUru;
import reactkr.powers.mayo.MM_14_tempPower1;

import java.util.ArrayList;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.atb;
import static reactkr.util.Wiz.att;

public class MM_54_tempPower1 extends AbstractAimedCard {
    public final static String ID = makeID("tempPower1");

    public MM_54_tempPower1() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = -5;
        baseSecondMagic = secondMagic = 5;
        useAim = true;
    }

    @Override
    public void normalUse(AbstractPlayer p, AbstractMonster m) {
        if (finalMagic == 0) {
            AbstractDungeon.effectList.add(
                    new TextAboveCreatureEffect(
                            p.hb.cX,
                            p.hb.cY,
                            "적어도 잃지는 않겠네요",
                            Settings.GREEN_TEXT_COLOR
                    )
            );
        } else {
            addToBot(new ApplyPowerAction(p, p, new MM_14_tempPower1(p, finalMagic)));
        }
    }

    @Override
    protected boolean useBullet() {
        return false;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(6);
    }
}
