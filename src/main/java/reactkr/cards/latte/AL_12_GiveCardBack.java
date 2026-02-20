package reactkr.cards.latte;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.actions.AL_11_SpinCardAction;
import reactkr.actions.PlayRandomCardsFromPoolAction;
import reactkr.cards.AbstractEasyCard_Latte;

import java.util.ArrayList;

import static reactkr.ModFile.makeID;

public class AL_12_GiveCardBack extends AbstractEasyCard_Latte {
    public final static String ID = makeID("GiveCardBack");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    private ArrayList<AbstractCard> cardPools = new ArrayList<>();
    public AL_12_GiveCardBack() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        initializeSummaryPreview();
        cardPools.add(new LimitBreak());
        cardPools.add(new Inflame());
        cardPools.add(new Flex());
        cardPools.add(new HeavyBlade());
        cardPools.add(new Armaments());
        cardPools.add(new SpotWeakness());
        cardPools.add(new Offering());
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            AbstractDungeon.actionManager.addToBottom(new PlayRandomCardsFromPoolAction(cardPools, AbstractDungeon.getCurrRoom().monsters.getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng), false));
        }
        this.addToBot(new AL_11_SpinCardAction(this, 0.5F, 6));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
        for (AbstractCard card : cardPools){
            card.upgrade();
        }
    }

    @Override
    protected int getPreviewDescriptionIndex() {
        return 0;
    }

    @Override
    protected int getPreviewUpgradeDescriptionIndex() {
        return -1;
    }

    @Override
    protected String getForbiddenStanceID() {
        return null;
    }

    @Override
    protected String getForbiddenMessage() {
        return null;
    }
}