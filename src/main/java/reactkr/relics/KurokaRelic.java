package reactkr.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import reactkr.cards.KurokaCard;

public class KurokaRelic extends AbstractRangerRelic {
    public static final String ID = RangerRelicConstants.KUROKA_ID;
    public KurokaRelic() { super(ID); }
    @Override protected AbstractCard createCard() { return new KurokaCard(); }
    @Override public AbstractRelic makeCopy() { return new KurokaRelic(); }
}