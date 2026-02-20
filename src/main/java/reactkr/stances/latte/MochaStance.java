package reactkr.stances.latte;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.stances.AbstractStance;
import reactkr.ModFile;

public class MochaStance extends AbstractStance {
    public static final String STANCE_ID = ModFile.makeID("Mocha");
    private static final StanceStrings stanceStrings;
    private static final String NAME;
    private static final String[] DESCRIPTION;
    public static final String CantUseMessage = "모카 상태에서는 사용할 수 없습니다.";

    public MochaStance() {
        this.ID = STANCE_ID;
        this.name = NAME;
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = stanceStrings.DESCRIPTION[0];
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return type == DamageInfo.DamageType.NORMAL ? damage * 2.0F : damage;
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        return type == DamageInfo.DamageType.NORMAL ? damage * 2.0F : damage;
    }

    static {
        stanceStrings = CardCrawlGame.languagePack.getStanceString(STANCE_ID);
        NAME = stanceStrings.NAME;
        DESCRIPTION = stanceStrings.DESCRIPTION;
    }
}
