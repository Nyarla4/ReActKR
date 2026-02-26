package reactkr.relics.kuroka;

import basemod.BaseMod;
import com.megacrit.cardcrawl.helpers.PowerTip;
import reactkr.Kuroka;
import reactkr.relics.AbstractEasyRelic;

import static reactkr.ModFile.makeID;

public class MK_04_MagicStickHammerRelic extends AbstractEasyRelic {
    public static final String ID = makeID("MagicStickHammerRelic");

    public MK_04_MagicStickHammerRelic() {
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
