package reactkr.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import reactkr.Kuroka;
import reactkr.Latte;
import reactkr.Mayo;

import static reactkr.ModFile.makeID;

public class DurianRelic extends AbstractEasyRelic {
    public static final String ID = makeID("DurianRelic");

    public DurianRelic() {
        super(ID, RelicTier.SPECIAL, LandingSound.FLAT, null);
    }

    public void onEquip() {
        AbstractDungeon.player.increaseMaxHp(20, true);
    }

    public AbstractRelic makeCopy() {
        return new DurianRelic();
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.chosenClass == Kuroka.Enums.THE_KUROKA ||
                AbstractDungeon.player.chosenClass == Mayo.Enums.THE_MAYO ||
                AbstractDungeon.player.chosenClass == Latte.Enums.THE_LATTE;
    }
}
