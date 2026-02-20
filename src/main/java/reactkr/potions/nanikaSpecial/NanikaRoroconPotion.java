package reactkr.potions.nanikaSpecial;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import reactkr.actions.AddRorokaHPAction;

import static reactkr.ModFile.makeID;

public class NanikaRoroconPotion extends AbstractPotion {
    public static String POTION_ID = makeID("NanikaRoroconPotion");
    private static final PotionStrings potionStrings;

    public NanikaRoroconPotion() {
        super(potionStrings.NAME, POTION_ID, PotionRarity.PLACEHOLDER, PotionSize.T, PotionColor.WEAK);
        this.isThrown = false;
    }

    public void initializeData() {
        this.potency = this.getPotency();
        this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(TipHelper.capitalize("로로카"), GameDictionary.keywords.get(makeID("로로카"))));
    }

    public void use(AbstractCreature target) {
        AbstractCreature var2 = AbstractDungeon.player;
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            addToBot(new AddRorokaHPAction(var2, var2, this.potency));
        }
    }

    public int getPotency(int ascensionLevel) {
        return 8;
    }

    public AbstractPotion makeCopy() {
        return new NanikaRoroconPotion();
    }

    static {
        potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    }
}