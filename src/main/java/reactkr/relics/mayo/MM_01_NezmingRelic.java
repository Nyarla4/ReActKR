package reactkr.relics.mayo;

import basemod.BaseMod;
import com.megacrit.cardcrawl.helpers.PowerTip;
import reactkr.Mayo;
import reactkr.relics.AbstractEasyRelic;

import static reactkr.ModFile.makeID;

public class MM_01_NezmingRelic extends AbstractEasyRelic {
    public static final String ID = makeID("NezmingRelic");

    public MM_01_NezmingRelic() {
        super(ID, RelicTier.SPECIAL, LandingSound.FLAT, Mayo.Enums.MAYO_COLOR);
    }

    @Override
    public void initializeTips(){
        super.initializeTips();

        String keyword = makeID("조준");
        this.tips.add(new PowerTip(BaseMod.getKeywordProper(keyword),
                BaseMod.getKeywordDescription(keyword)));
    }
}
