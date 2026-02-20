package reactkr.potions.nanikaSpecial;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

import static reactkr.ModFile.makeID;

public class NanikaFirePotion extends AbstractPotion {
    public static String POTION_ID = makeID("NanikaFirePotion");
    private static final PotionStrings potionStrings;

    public NanikaFirePotion() {
        super(potionStrings.NAME, POTION_ID, PotionRarity.PLACEHOLDER, PotionSize.SPHERE, PotionColor.FIRE);
        this.isThrown = true;
        this.targetRequired = true;
    }

    public void initializeData() {
        this.potency = this.getPotency();
        this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    public void use(AbstractCreature target) {
        DamageInfo info = new DamageInfo(AbstractDungeon.player, this.potency, DamageInfo.DamageType.THORNS);
        info.applyEnemyPowersOnly(target);
        this.addToBot(new DamageAction(target, info, AbstractGameAction.AttackEffect.FIRE));
    }

    public int getPotency(int ascensionLevel) {
        return 20;
    }

    public AbstractPotion makeCopy() {
        return new NanikaFirePotion();
    }

    static {
        potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    }
}