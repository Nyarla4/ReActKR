package reactkr.stances.mayo;

import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.stances.AbstractStance;
import reactkr.ModFile;

public class ApexStance extends AbstractStance {
    public static final String STANCE_ID = ModFile.makeID("Apex");
    private static final StanceStrings stanceStrings;
    private static final String NAME;
    private static final String[] DESCRIPTION;

    public ApexStance() {
        this.ID = STANCE_ID;
        this.name = NAME;
        this.updateDescription();
    }

    @Override
    public void onExitStance() {
        AbstractDungeon.actionManager.addToTop(new EvokeOrbAction(1));
    }


    @Override
    public void updateDescription() {
        this.description = stanceStrings.DESCRIPTION[0];
    }

    static {
        stanceStrings = CardCrawlGame.languagePack.getStanceString(STANCE_ID);
        NAME = stanceStrings.NAME;
        DESCRIPTION = stanceStrings.DESCRIPTION;
    }
}
