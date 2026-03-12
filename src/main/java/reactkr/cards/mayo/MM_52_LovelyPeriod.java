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
import reactkr.cards.subcards.MM_50_NowYouNezming;
import reactkr.cards.subcards.MM_52_MayoKuroka;
import reactkr.cards.subcards.MM_52_MayoSiah;
import reactkr.cards.subcards.MM_52_MayoUru;
import reactkr.powers.mayo.MM_11_SantaNezmingPower;

import java.util.ArrayList;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.atb;
import static reactkr.util.Wiz.att;

public class MM_52_LovelyPeriod extends AbstractEasyCard_Mayo {
    public final static String ID = makeID("LovelyPeriod");

    AbstractCard previewCard01 = new MM_52_MayoSiah();
    AbstractCard previewCard02 = new MM_52_MayoUru();
    AbstractCard previewCard03 = new MM_52_MayoKuroka();

    public MM_52_LovelyPeriod() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        MultiCardPreview.add(this, AbstractDungeon.player != null, this.previewCard01, this.previewCard02, this.previewCard03);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded) {
            ArrayList<AbstractCard> easyCardList = new ArrayList<>();

            easyCardList.add(new EasyModalChoiceCard(
                    previewCard01.cardID,
                    previewCard01.name,
                    cardStrings.EXTENDED_DESCRIPTION[0],
                    () -> att(new MakeTempCardInHandAction(previewCard01.makeCopy(), 1))
            ));
            easyCardList.add(new EasyModalChoiceCard(
                    previewCard02.cardID,
                    previewCard02.name,
                    cardStrings.EXTENDED_DESCRIPTION[1],
                    () -> att(new MakeTempCardInHandAction(previewCard02.makeCopy(), 1))
            ));
            easyCardList.add(new EasyModalChoiceCard(
                    previewCard03.cardID,
                    previewCard03.name,
                    cardStrings.EXTENDED_DESCRIPTION[2],
                    () -> att(new MakeTempCardInHandAction(previewCard03.makeCopy(), 1))
            ));

            atb(new EasyModalChoiceAction(easyCardList));
        } else {
            int randomValue = AbstractDungeon.cardRandomRng.random(2);
            AbstractCard card;
            switch (randomValue) {
                case 1:
                    card = this.previewCard02;
                    break;
                case 2:
                    card = this.previewCard03;
                    break;
                case 0:
                default:
                    card = this.previewCard01;
                    break;
            }
            att(new MakeTempCardInHandAction(card.makeStatEquivalentCopy(), 1));
        }
    }

    @Override
    public void upp() {
    }
}
