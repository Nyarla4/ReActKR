package reactkr.potions.nanikaSpecial;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
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
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import static reactkr.ModFile.makeID;

public class NanikaDexterityPotion extends AbstractPotion {
    public static String POTION_ID = makeID("NanikaDexterityPotion");
    private static final PotionStrings potionStrings;

    public NanikaDexterityPotion() {
        super(potionStrings.NAME, POTION_ID, PotionRarity.PLACEHOLDER, PotionSize.S, PotionColor.GREEN);
        this.isThrown = false;
    }

    public void initializeData() {
        this.potency = this.getPotency();
        this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(TipHelper.capitalize(GameDictionary.DEXTERITY.NAMES[0]), (String)GameDictionary.keywords.get(GameDictionary.DEXTERITY.NAMES[0])));
    }

    public void use(AbstractCreature target) {
        AbstractCreature var2 = AbstractDungeon.player;
        AbstractDungeon.effectList.add(new FlashAtkImgEffect(var2.hb.cX, var2.hb.cY, AbstractGameAction.AttackEffect.SHIELD));
        this.addToBot(new ApplyPowerAction(var2, AbstractDungeon.player, new DexterityPower(var2, this.potency), this.potency));
    }

    public int getPotency(int ascensionLevel) {
        return 1;
    }

    public AbstractPotion makeCopy() {
        return new NanikaDexterityPotion();
    }

    static {
        potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    }
}