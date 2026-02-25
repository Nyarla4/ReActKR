package reactkr.relics.kuroka;

import basemod.BaseMod;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import reactkr.Kuroka;
import reactkr.actions.AddRorokaHPAction;
import reactkr.relics.AbstractEasyRelic;

import static reactkr.ModFile.makeID;

public class MK_03_RoroRibbonRelic extends AbstractEasyRelic {
    public static final String ID = makeID("RoroRibbonRelic");

    public MK_03_RoroRibbonRelic() {
        super(ID, RelicTier.RARE, LandingSound.FLAT, Kuroka.Enums.KUROKA_COLOR);
    }

    @Override
    public void onPlayerEndTurn() {
        addToBot(new AddRorokaHPAction(AbstractDungeon.player, AbstractDungeon.player, 3));
    }

    @Override
    public void initializeTips(){
        super.initializeTips();

        String keyword = makeID("로로카");
        this.tips.add(new PowerTip(BaseMod.getKeywordProper(keyword),
                BaseMod.getKeywordDescription(keyword)));
    }
}
