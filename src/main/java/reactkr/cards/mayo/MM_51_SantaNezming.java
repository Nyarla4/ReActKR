package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.actions.CardChangeAction;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.cards.subcards.MM_50_NowYouNezming;
import reactkr.powers.mayo.MM_03_EvasionPower;
import reactkr.powers.mayo.MM_11_SantaNezmingPower;

import static reactkr.ModFile.makeID;

public class MM_51_SantaNezming extends AbstractEasyCard_Mayo {
    public final static String ID = makeID("SantaNezming");

    public MM_51_SantaNezming() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        cardsToPreview = new MM_50_NowYouNezming();
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new MM_11_SantaNezmingPower(p, magicNumber)));
    }

    @Override
    public void upp() {
        updateCost(-1);
    }
}
