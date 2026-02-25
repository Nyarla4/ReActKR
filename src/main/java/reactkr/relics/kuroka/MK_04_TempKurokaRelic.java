package reactkr.relics.kuroka;

import basemod.BaseMod;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import reactkr.Kuroka;
import reactkr.actions.AddRorokaHPAction;
import reactkr.relics.AbstractEasyRelic;

import static reactkr.ModFile.makeID;

public class MK_04_TempKurokaRelic extends AbstractEasyRelic {
    public static final String ID = makeID("TempKurokaRelic");

    public MK_04_TempKurokaRelic() {
        super(ID, RelicTier.UNCOMMON, LandingSound.FLAT, Kuroka.Enums.KUROKA_COLOR);
    }

    @Override
    public void initializeTips(){
        super.initializeTips();

        String keyword = makeID("주문");
        this.tips.add(new PowerTip(BaseMod.getKeywordProper(keyword),
                BaseMod.getKeywordDescription(keyword)));
    }
}
