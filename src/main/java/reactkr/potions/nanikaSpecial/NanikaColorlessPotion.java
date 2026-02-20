package reactkr.potions.nanikaSpecial;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import reactkr.actions.LesserDiscoveryAction;

import static reactkr.ModFile.makeID;

public class NanikaColorlessPotion extends AbstractPotion {
    public static String POTION_ID = makeID("NanikaColorlessPotion");
    private static final PotionStrings potionStrings;

    public NanikaColorlessPotion() {
        super(potionStrings.NAME, POTION_ID, PotionRarity.PLACEHOLDER, PotionSize.CARD, PotionColor.WHITE);
        this.isThrown = false;
    }

    public void initializeData() {
        this.potency = this.getPotency();
        if (AbstractDungeon.player != null && AbstractDungeon.player.hasRelic("SacredBark")) {
            this.description = potionStrings.DESCRIPTIONS[1];
        } else {
            this.description = potionStrings.DESCRIPTIONS[0];
        }

        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    public void use(AbstractCreature target) {
        this.addToBot(new LesserDiscoveryAction(true, this.potency));
    }

    public int getPotency(int ascensionLevel) {
        return 1;
    }

    public AbstractPotion makeCopy() {
        return new NanikaColorlessPotion();
    }

    static {
        potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    }
}