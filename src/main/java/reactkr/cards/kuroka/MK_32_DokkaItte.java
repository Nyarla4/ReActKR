package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.cards.subcards.MK_32_Jujutsu;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.atb;

public class MK_32_DokkaItte extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("DokkaItte");
    // intellij stuff skill, self, common, , , , , 1, 2
    AbstractCard previewCard = new MK_32_Jujutsu();

    public MK_32_DokkaItte() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.cardsToPreview = previewCard;
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

        atb(new MakeTempCardInHandAction(previewCard.makeCopy(), count));
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}
