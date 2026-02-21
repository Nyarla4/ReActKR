package reactkr.cards.kuroka;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.actions.EasyModalChoiceAction;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.cards.EasyModalChoiceCard;
import reactkr.cards.subcards.MK_20_ColorBlack;
import reactkr.cards.subcards.MK_20_ColorRed;
import reactkr.powers.kuroka.MK_15_RoroGlowing_Power;

import java.util.ArrayList;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.*;

public class MK_21_ColorKurokaLike extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("ColorKurokaLike");
    // intellij stuff skill, self, uncommon, , , , , ,

    AbstractCard previewCard01 =new MK_20_ColorBlack();
    AbstractCard previewCard02 =new MK_20_ColorRed();
    private static final Logger logger = LogManager.getLogger(MK_21_ColorKurokaLike.class.getName());

    public MK_21_ColorKurokaLike() {
        super(ID, 1, CardType.SKILL, AbstractCard.CardRarity.UNCOMMON, CardTarget.NONE);
        exhaust = true;
        MultiCardPreview.add(this,this.previewCard01 ,this.previewCard02 );
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        // 특정 카드 두 장을 정의 (클래스명에 맞게 수정하세요)
        AbstractCard cardA = this.previewCard01;
        AbstractCard cardB = this.previewCard02;
        logger.info(cardA.upgraded);
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
        this.previewCard01.upgrade();
        this.previewCard02.upgrade();
        MultiCardPreview.multiCardPreview.get(this).forEach(AbstractCard::upgrade); // here, we show upgraded smite and safeties.
    }
}
