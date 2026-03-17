package reactkr.cards.mayo;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.actions.EasyModalChoiceAction;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.cards.EasyModalChoiceCard;
import reactkr.cards.subcards.MM_52_MayoKuroka;
import reactkr.cards.subcards.MM_52_MayoSiah;
import reactkr.cards.subcards.MM_52_MayoUru;
import reactkr.powers.mayo.MM_14_tempPower1;
import reactkr.powers.mayo.MM_15_tempPower2;

import java.util.ArrayList;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.atb;
import static reactkr.util.Wiz.att;

public class MM_55_tempPower2 extends AbstractEasyCard_Mayo {
    public final static String ID = makeID("tempPower2");

    public MM_55_tempPower2() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = -5;
        baseSecondMagic = secondMagic = 5;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new MM_15_tempPower2(p, magicNumber, secondMagic)));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(6);
    }
}
