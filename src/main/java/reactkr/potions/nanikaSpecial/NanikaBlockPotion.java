package reactkr.potions.nanikaSpecial;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

import static reactkr.ModFile.makeID;

public class NanikaBlockPotion extends AbstractPotion {
    public static String POTION_ID = makeID("NanikaBlockPotion");
    private static final PotionStrings potionStrings;

    public NanikaBlockPotion() {
        super(potionStrings.NAME, POTION_ID, PotionRarity.PLACEHOLDER, PotionSize.S, PotionColor.BLUE);
        this.isThrown = false;
    }

    public void initializeData() {
        this.potency = this.getPotency();
        this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(TipHelper.capitalize(GameDictionary.BLOCK.NAMES[0]), (String)GameDictionary.keywords.get(GameDictionary.BLOCK.NAMES[0])));
    }

    public void use(AbstractCreature target) {
        this.addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.potency));
    }

    public int getPotency(int ascensionLevel) {
        return 10;
    }

    public AbstractPotion makeCopy() {
        return new NanikaBlockPotion();
    }

    static {
        potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    }
}