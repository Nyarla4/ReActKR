package reactkr.relics;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import reactkr.Kuroka;
import reactkr.Latte;
import reactkr.Mayo;
import reactkr.cards.UruCard;

import static reactkr.ModFile.makeID;

public class MurimpanRelic extends AbstractEasyRelic {
    public static final String ID = makeID("MurimpanRelic");

    private AbstractCard.CardType lastType = null;

    public MurimpanRelic() {
        super(ID, RelicTier.RARE, LandingSound.FLAT, null);
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (lastType == null) {
            lastType = c.type;
            return;
        }

        if (lastType != c.type) {
            flash();
            addToBot(new GainBlockAction(AbstractDungeon.player, 2));
            lastType = c.type;
        }
    }

    public AbstractRelic makeCopy() {
        return new MurimpanRelic();
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.chosenClass == Kuroka.Enums.THE_KUROKA ||
                AbstractDungeon.player.chosenClass == Mayo.Enums.THE_MAYO ||
                AbstractDungeon.player.chosenClass == Latte.Enums.THE_LATTE;
    }
}
