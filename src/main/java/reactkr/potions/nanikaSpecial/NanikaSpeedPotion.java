package reactkr.potions.nanikaSpecial;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static reactkr.ModFile.makeID;

public class NanikaSpeedPotion extends AbstractPotion {
    public static String POTION_ID = makeID("NanikaSpeedPotion");
    private static final PotionStrings potionStrings;

    public NanikaSpeedPotion() {
        super(potionStrings.NAME, POTION_ID, PotionRarity.PLACEHOLDER, PotionSize.BOLT, PotionColor.SKILL);
        this.isThrown = false;
    }

    public void initializeData() {
        this.potency = this.getPotency();
        this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1] + this.potency + potionStrings.DESCRIPTIONS[2];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(TipHelper.capitalize(GameDictionary.DEXTERITY.NAMES[0]), (String)GameDictionary.keywords.get(GameDictionary.DEXTERITY.NAMES[0])));
    }

    public void use(AbstractCreature target) {
        AbstractCreature var2 = AbstractDungeon.player;
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            this.addToBot(new ApplyPowerAction(var2, AbstractDungeon.player, new DexterityPower(var2, this.potency), this.potency));
            this.addToBot(new ApplyPowerAction(var2, AbstractDungeon.player, new LoseDexterityPower(var2, this.potency), this.potency));
        }

    }

    public int getPotency(int ascensionLevel) {
        return 4;
    }

    public AbstractPotion makeCopy() {
        return new NanikaSpeedPotion();
    }

    static {
        potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    }
}