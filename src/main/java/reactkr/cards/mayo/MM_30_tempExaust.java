package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.powers.mayo.MM_07_tempExaustPower;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.applyToSelf;
import static reactkr.util.Wiz.atb;

public class MM_30_tempExaust extends AbstractEasyCard_Mayo {
    public final static String ID = makeID("tempExaust");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,
    private static final Logger logger = LogManager.getLogger(MM_30_tempExaust.class.getName());

    public MM_30_tempExaust() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 1;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractCard c : p.hand.group) {
            if (c.equals(this))//이 카드 포함이라서 제외
            {
                continue;
            }
            atb(new ExhaustSpecificCardAction(c, p.hand, false));
        }
        applyToSelf(new MM_07_tempExaustPower(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}