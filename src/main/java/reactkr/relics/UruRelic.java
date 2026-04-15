package reactkr.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import reactkr.cards.UruCard;

public class UruRelic extends AbstractRangerRelic {
    public static final String ID = RangerRelicConstants.URU_ID;
    public UruRelic() { super(ID); }
    @Override protected AbstractCard createCard() { return new UruCard(); }
    @Override public AbstractRelic makeCopy() { return new UruRelic(); }
}