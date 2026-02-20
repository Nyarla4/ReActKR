package reactkr.potions.nanikaSpecial;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.GamblingChipAction;
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
import reactkr.actions.LimitedGamblingChipAction;

import static reactkr.ModFile.makeID;

public class NanikaGamblingPotion extends AbstractPotion {
    public static String POTION_ID = makeID("NanikaGamblingPotion");
    private static final PotionStrings potionStrings;

    public NanikaGamblingPotion() {
        super(potionStrings.NAME, POTION_ID, PotionRarity.PLACEHOLDER, PotionSize.S, PotionColor.SMOKE);
        this.isThrown = false;
    }

    public void initializeData() {
        this.potency = this.getPotency();
        this.description = this.potency + potionStrings.DESCRIPTIONS[0];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    public void use(AbstractCreature target) {
        if (!AbstractDungeon.player.hand.isEmpty()) {
            this.addToBot(new LimitedGamblingChipAction(AbstractDungeon.player, this.potency));
        }

    }

    public int getPotency(int ascensionLevel) {
        return 4;
    }

    public AbstractPotion makeCopy() {
        return new NanikaGamblingPotion();
    }

    static {
        potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    }
}