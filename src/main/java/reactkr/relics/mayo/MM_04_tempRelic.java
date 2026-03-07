package reactkr.relics.mayo;

import basemod.BaseMod;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import reactkr.Mayo;
import reactkr.relics.AbstractEasyRelic;

import static reactkr.ModFile.makeID;

public class MM_04_tempRelic extends AbstractEasyRelic {
    public static final String ID = makeID("tempRelic");

    public MM_04_tempRelic() {
        super(ID, RelicTier.COMMON, LandingSound.FLAT, Mayo.Enums.MAYO_COLOR);
    }

    public AbstractRelic makeCopy() {
        return new MM_04_tempRelic();
    }

    @Override
    public void initializeTips() {
        super.initializeTips();

        String keyword = makeID("회피");
        this.tips.add(new PowerTip(BaseMod.getKeywordProper(keyword),
                BaseMod.getKeywordDescription(keyword)));
    }
}
