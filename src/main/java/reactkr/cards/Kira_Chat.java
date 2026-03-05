package reactkr.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import reactkr.actions.TransformCardInHandAction;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.applyToSelf;

public class Kira_Chat extends AbstractEasyCard {
    public final static String ID = makeID("Kira_Chat");

    public Kira_Chat() {
        this(false);
    }

    public Kira_Chat(boolean isPreview) {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE, CardColor.COLORLESS);
        baseMagicNumber = magicNumber = 6;
        selfRetain = true;
        if (!isPreview) {
            cardsToPreview = new Kira_Song(true);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new StrengthPower(p, magicNumber));
        applyToSelf(new LoseStrengthPower(p, this.magicNumber));
    }

    @Override
    public void upp() {
        if (cardsToPreview != null) {
            cardsToPreview.upgrade();
        }
        upgradeMagicNumber(2);
    }

    @Override
    public void onRetained() {
        AbstractCard target = new Kira_Song();
        if(upgraded){
            target.upgrade();
        }
        addToTop(new TransformCardInHandAction(this, target.makeStatEquivalentCopy()));
    }
}
