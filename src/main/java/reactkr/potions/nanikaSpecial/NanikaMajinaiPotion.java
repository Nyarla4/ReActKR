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
import reactkr.powers.kuroka.MK_01_Majinai_Power;

import static reactkr.ModFile.makeID;

public class NanikaMajinaiPotion extends AbstractPotion {
    public static String POTION_ID = makeID("NanikaMajinaiPotion");
    private static final PotionStrings potionStrings;

    public NanikaMajinaiPotion() {
        super(potionStrings.NAME, POTION_ID, PotionRarity.PLACEHOLDER, PotionSize.SPHERE, PotionColor.WEAK);
        this.isThrown = true;
        this.targetRequired = true;
    }

    public void initializeData() {
        this.potency = this.getPotency();
        this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(TipHelper.capitalize("주문"), GameDictionary.keywords.get(makeID("주문"))));
        this.tips.add(new PowerTip(TipHelper.capitalize("주문강화"), GameDictionary.keywords.get(makeID("주문강화"))));
    }

    public void use(AbstractCreature target) {
        this.addToBot(new ApplyPowerAction(target, AbstractDungeon.player, new MK_01_Majinai_Power(target, this.potency)));
    }

    public int getPotency(int ascensionLevel) {
        return 6;
    }

    public AbstractPotion makeCopy() {
        return new NanikaMajinaiPotion();
    }

    static {
        potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    }
}