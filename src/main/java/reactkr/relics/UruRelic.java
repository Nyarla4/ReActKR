package reactkr.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import reactkr.Kuroka;
import reactkr.Latte;
import reactkr.Mayo;
import reactkr.cards.UruCard;

import static reactkr.ModFile.makeID;

public class UruRelic extends AbstractEasyRelic {
    public static final String ID = makeID("UruRelic");

    public UruRelic() {
        super(ID, RelicTier.RARE, LandingSound.FLAT, null);
    }

    public void onEquip() {
        AbstractCard card = new UruCard();

        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(
                card,
                (float) Settings.WIDTH / 2.0F,
                (float)Settings.HEIGHT / 2.0F
        ));
    }

    public AbstractRelic makeCopy() {
        return new UruRelic();
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.chosenClass.equals(Kuroka.Enums.THE_KUROKA) ||
                AbstractDungeon.player.chosenClass.equals(Mayo.Enums.THE_MAYO) ||
                AbstractDungeon.player.chosenClass.equals(Latte.Enums.THE_LATTE);
    }
}
