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
import com.megacrit.cardcrawl.powers.WeakPower;

import static reactkr.ModFile.makeID;

public class NanikaWeakenPotion extends AbstractPotion {
    public static String POTION_ID = makeID("NanikaWeakenPotion");
    private static final PotionStrings potionStrings;

    public NanikaWeakenPotion() {
        super(potionStrings.NAME, POTION_ID, PotionRarity.PLACEHOLDER, PotionSize.H, PotionColor.WEAK);
        this.isThrown = true;
        this.targetRequired = true;
    }

    public void initializeData() {
        this.potency = this.getPotency();
        this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(TipHelper.capitalize(GameDictionary.WEAK.NAMES[0]), (String)GameDictionary.keywords.get(GameDictionary.WEAK.NAMES[0])));
    }

    public void use(AbstractCreature target) {
        this.addToBot(new ApplyPowerAction(target, AbstractDungeon.player, new WeakPower(target, this.potency, false), this.potency));
    }

    public int getPotency(int ascensionLevel) {
        return 2;
    }

    public AbstractPotion makeCopy() {
        return new NanikaWeakenPotion();
    }

    static {
        potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    }
}