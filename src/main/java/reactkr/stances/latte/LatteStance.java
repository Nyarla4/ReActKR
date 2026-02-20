package reactkr.stances.latte;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.stances.AbstractStance;
import reactkr.ModFile;

public class LatteStance extends AbstractStance {
    public static final String STANCE_ID = ModFile.makeID("Latte");
    private static final StanceStrings stanceStrings;
    private static final String NAME;
    private static final String[] DESCRIPTION;

    public LatteStance() {
        this.ID = STANCE_ID;
        this.name = NAME;
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = stanceStrings.DESCRIPTION[0];
    }

    public void onEnterStance() {

    }

    public void onExitStance() {

    }

    public void atStartOfTurn() {

    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return damage;
    }

    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        return damage;
    }

    static {
        stanceStrings = CardCrawlGame.languagePack.getStanceString(STANCE_ID);
        NAME = stanceStrings.NAME;
        DESCRIPTION = stanceStrings.DESCRIPTION;
    }
}
