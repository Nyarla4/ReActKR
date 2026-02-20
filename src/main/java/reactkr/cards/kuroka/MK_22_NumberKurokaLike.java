package reactkr.cards.kuroka;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.actions.EasyModalChoiceAction;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.cards.EasyModalChoiceCard;
import reactkr.cards.subcards.MK_21_NumberFour;
import reactkr.cards.subcards.MK_21_NumberSix;

import java.util.ArrayList;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.atb;
import static reactkr.util.Wiz.att;

public class MK_22_NumberKurokaLike extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("NumberKurokaLike");
    // intellij stuff skill, self, uncommon, , , , , ,

    AbstractCard previewCard01 =new MK_21_NumberFour();
    AbstractCard previewCard02 =new MK_21_NumberSix();

    public MK_22_NumberKurokaLike() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        MultiCardPreview.add(this,previewCard01 ,previewCard02 );
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        // 특정 카드 두 장을 정의 (클래스명에 맞게 수정하세요)
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
                    cardStrings.EXTENDED_DESCRIPTION[0],
                    () -> att(new MakeTempCardInHandAction(cardA.makeCopy(), 1))
            ));

            // 선택지 2: 카드 B
            easyCardList.add(new EasyModalChoiceCard(
                    cardB.cardID,
                    cardB.name,
                    cardStrings.EXTENDED_DESCRIPTION[1],
                    () -> att(new MakeTempCardInHandAction(cardB.makeCopy(), 1))
            ));

            atb(new EasyModalChoiceAction(easyCardList));
        }
    }

    @Override
    public void upp() {
        previewCard01.upgrade();
        previewCard02.upgrade();
        MultiCardPreview.multiCardPreview.get(this).forEach(c -> c.upgrade()); // here, we show upgraded smite and safeties.
    }
}
