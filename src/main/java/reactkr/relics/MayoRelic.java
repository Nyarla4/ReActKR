package reactkr.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import reactkr.cards.MayoCard;

public class MayoRelic extends AbstractRangerRelic {
    public static final String ID = RangerRelicConstants.MAYO_ID;
    public MayoRelic() { super(ID); }
    @Override protected AbstractCard createCard() { return new MayoCard(); }
    @Override public AbstractRelic makeCopy() { return new MayoRelic(); }
}