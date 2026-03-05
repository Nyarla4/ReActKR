package reactkr.relics.mayo;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import reactkr.Mayo;
import reactkr.actions.SelectDiscardAction;
import reactkr.relics.AbstractEasyRelic;

import static reactkr.ModFile.makeID;

public class MM_00_MayoStartItemRelic extends AbstractEasyRelic {
    public static final String ID = makeID("MayoStartItemRelic");

    public MM_00_MayoStartItemRelic() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, Mayo.Enums.MAYO_COLOR);
    }

    @Override
    public void atBattleStart(){
        this.flash();
        addToBot(new DrawCardAction(1));
        addToBot(new SelectDiscardAction(2));
    }
}
