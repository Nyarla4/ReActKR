package reactkr.relics.latte;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import reactkr.Latte;
import reactkr.relics.AbstractEasyRelic;

import static reactkr.ModFile.makeID;

public class AL_02_CursePassRelic extends AbstractEasyRelic {
    public static final String ID = makeID("CursePassRelic");

    public AL_02_CursePassRelic() {
        super(ID, RelicTier.COMMON, LandingSound.FLAT, Latte.Enums.LATTE_COLOR);
    }

    public AbstractRelic makeCopy() {
        return new AL_02_CursePassRelic();
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.CURSE) {
            AbstractDungeon.player.getRelic("Blue Candle").flash();
            card.setCostForTurn(0);
            card.exhaust = true;
            action.exhaustCard = true;
        }

    }
}
