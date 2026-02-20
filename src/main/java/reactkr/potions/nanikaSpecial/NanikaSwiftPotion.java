package reactkr.potions.nanikaSpecial;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

import static reactkr.ModFile.makeID;

public class NanikaSwiftPotion extends AbstractPotion {
    public static String POTION_ID = makeID("NanikaSwiftPotion");
    private static final PotionStrings potionStrings;

    public NanikaSwiftPotion() {
        super(potionStrings.NAME, POTION_ID, PotionRarity.PLACEHOLDER, PotionSize.H, PotionColor.SWIFT);
        this.isThrown = false;
    }

    public void initializeData() {
        this.potency = this.getPotency();
        this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    public void use(AbstractCreature target) {
        this.addToBot(new DrawCardAction(AbstractDungeon.player, this.potency));
    }

    public int getPotency(int ascensionLevel) {
        return 2;
    }

    public AbstractPotion makeCopy() {
        return new NanikaSwiftPotion();
    }

    static {
        potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    }
}