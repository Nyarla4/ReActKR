package reactkr.relics.mayo;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import reactkr.Mayo;
import reactkr.actions.SelectDiscardAction;
import reactkr.relics.AbstractEasyRelic;

import static reactkr.ModFile.makeID;

public class MM_06_ToiletRelic extends AbstractEasyRelic {
    public static final String ID = makeID("ToiletRelic");

    public MM_06_ToiletRelic() {
        super(ID, RelicTier.UNCOMMON, LandingSound.FLAT, Mayo.Enums.MAYO_COLOR);
    }

    @Override
    public void atBattleStart(){
        this.flash();
        addToBot(new DrawCardAction(1));
        addToBot(new SelectDiscardAction(2));
    }
}
