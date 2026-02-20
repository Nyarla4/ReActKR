package reactkr.potions.nanikaSpecial;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.RegenPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static reactkr.ModFile.makeID;

public class NanikaMemoriesPotion extends AbstractPotion {
    public static String POTION_ID = makeID("NanikaMemoriesPotion");
    private static final PotionStrings potionStrings;

    public NanikaMemoriesPotion() {
        super(potionStrings.NAME, POTION_ID, PotionRarity.PLACEHOLDER, PotionSize.EYE, PotionEffect.NONE, new Color(225754111), new Color(389060095), (Color)null);
        this.isThrown = false;
    }

    public void initializeData() {
        this.potency = this.getPotency();
        if (this.potency == 1) {
            this.description = potionStrings.DESCRIPTIONS[0];
        } else {
            this.description = potionStrings.DESCRIPTIONS[1] + this.potency + potionStrings.DESCRIPTIONS[2];
        }

        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    public void use(AbstractCreature target) {
        this.addToBot(new BetterDiscardPileToHandAction(this.potency, 0));
    }

    public int getPotency(int ascensionLevel) {
        return 1;
    }

    public AbstractPotion makeCopy() {
        return new NanikaMemoriesPotion();
    }

    static {
        potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    }
}