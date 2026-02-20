package reactkr.relics.latte;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.powers.StrengthPower;
import reactkr.Latte;
import reactkr.relics.AbstractEasyRelic;

import static reactkr.ModFile.makeID;

public class AL_01_TtediRelic extends AbstractEasyRelic {
    public static final String ID = makeID("TtediRelic");

    public AL_01_TtediRelic() {
        super(ID, RelicTier.SPECIAL, LandingSound.FLAT, Latte.Enums.LATTE_COLOR);
    }

    @Override
    public void initializeTips(){
        super.initializeTips();

        String keyword = makeID("아침");
        this.tips.add(new PowerTip(BaseMod.getKeywordProper(keyword),
                BaseMod.getKeywordDescription(keyword)));
    }
}
