package reactkr.stances.latte;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.stances.AbstractStance;
import reactkr.ModFile;

public class AotoStance extends AbstractStance {
    public static final String STANCE_ID = ModFile.makeID("Aoto");
    private static final StanceStrings stanceStrings;
    private static final String NAME;
    private static final String[] DESCRIPTION;

    public AotoStance() {
        this.ID = STANCE_ID;
        this.name = NAME;
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = stanceStrings.DESCRIPTION[0];
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return type == DamageInfo.DamageType.NORMAL ? damage * 0.5F : damage;
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        return type == DamageInfo.DamageType.NORMAL ? damage * 0.5F : damage;
    }

    static {
        stanceStrings = CardCrawlGame.languagePack.getStanceString(STANCE_ID);
        NAME = stanceStrings.NAME;
        DESCRIPTION = stanceStrings.DESCRIPTION;
    }
}
