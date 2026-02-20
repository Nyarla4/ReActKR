package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.actions.EasyModalChoiceAction;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.cards.EasyModalChoiceCard;

import java.util.ArrayList;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.atb;
import static reactkr.util.Wiz.att;

public class MK_12_LostDelusion extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("LostDelusion");
    // intellij stuff skill, none, common, , , , , ,

    public MK_12_LostDelusion() {
        super(ID, 1, AbstractCard.CardType.SKILL, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.NONE);
        this.isEthereal = true;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = 0;
        for (AbstractCard c : p.hand.group){
            if(c.equals(this))//이 카드 포함이라서 제외시키려고
            {
                continue;
            }
            atb(new ExhaustSpecificCardAction(c, p.hand, false));
            count++;
        }

        // 3. 익명 액션을 사용하여 '버린 후' 로직 처리
        int finalCount = count;
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                // 버린 카드 수만큼 무작위 카드 생성 루프
                for (int i = 0; i < finalCount; i++) {
                    // 무작위 카드 생성
                    AbstractCard cb = AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy();

                    cb.updateCost(-1);

                    if(upgraded){
                        cb.upgrade();
                    }

                    // 핸드에 카드 추가 액션 (addToTop을 써서 생성 순서를 유지할 수 있음)
                    addToTop(new MakeTempCardInHandAction(cb, 1));
                }

                this.isDone = true; // 액션 완료 처리
            }
        });
    }

    public void upp() {
        //this.isEthereal = false;
        upgradeBaseCost(0);
    }
}
