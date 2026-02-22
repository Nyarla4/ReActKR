package reactkr.cards.mayo;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
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

public class MM_12_SevenOrFive extends AbstractEasyCard_Mayo {
    public final static String ID = makeID("SevenOrFive");
    // intellij stuff skill, self, uncommon, , , , , ,

    AbstractCard previewCard01 = new MM_12_Seven();
    AbstractCard previewCard02 = new MM_12_Five();

    public MM_12_SevenOrFive() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 7;
        MultiCardPreview.add(this,this.previewCard01 ,this.previewCard02 );
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudio(ProAudio.SEVENORFIVE);
        AbstractCard cardA = previewCard01;
        AbstractCard cardB = previewCard02;

        if (this.upgraded) {
            // [강화 시]: 선택 없이 두 장 모두 패로 가져옴
            att(new MakeTempCardInHandAction(cardA.makeStatEquivalentCopy(), 1));
            att(new MakeTempCardInHandAction(cardB.makeStatEquivalentCopy(), 1));
        } else {
            // [통상 시]: 둘 중 하나를 선택
            ArrayList<AbstractCard> easyCardList = new ArrayList<>();

            // 선택지 1: 카드 A
            easyCardList.add(new EasyModalChoiceCard(
                    cardA.cardID,
                    cardA.name,
                    upgraded ? cardStrings.EXTENDED_DESCRIPTION[1] : cardStrings.EXTENDED_DESCRIPTION[0],
                    () -> att(new MakeTempCardInHandAction(cardA.makeCopy(), 1))
            ));

            // 선택지 2: 카드 B
            easyCardList.add(new EasyModalChoiceCard(
                    cardB.cardID,
                    cardB.name,
                    cardStrings.EXTENDED_DESCRIPTION[2],
                    () -> att(new MakeTempCardInHandAction(cardB.makeCopy(), 1))
            ));

            atb(new EasyModalChoiceAction(easyCardList));
        }
    }

    @Override
    public void upp() {
        previewCard01.upgrade();
        upgradeMagicNumber(7);
        MultiCardPreview.multiCardPreview.get(this).forEach(c -> c.upgrade()); // here, we show upgraded smite and safeties.
    }
}
