package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.actions.CustomCallbackExhaustAction;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.powers.mayo.MM_16_TrainingPower;

import static reactkr.ModFile.makeID;

public class MM_57_MayoDororong extends AbstractEasyCard_Mayo {
    public final static String ID = makeID("MayoDororong");

    public MM_57_MayoDororong() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        baseSecondMagic = secondMagic = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new CustomCallbackExhaustAction(magicNumber, false, (exhaustedCards) -> {
            for (AbstractCard c : exhaustedCards) {
                if (c.type == CardType.CURSE || c.type == CardType.STATUS) {
                    addToBot(new GainEnergyAction(1));
                }
            }
        }));
        addToBot(new DrawCardAction(secondMagic));
    }

    @Override
    public void upp() {
        updateCost(-1);
    }
}
