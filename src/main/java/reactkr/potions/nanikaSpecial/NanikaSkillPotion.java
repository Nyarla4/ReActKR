package reactkr.potions.nanikaSpecial;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import reactkr.actions.LesserDiscoveryAction;

import static reactkr.ModFile.makeID;

public class NanikaSkillPotion extends AbstractPotion {
    public static String POTION_ID = makeID("NanikaSkillPotion");
    private static final PotionStrings potionStrings;

    public NanikaSkillPotion() {
        super(potionStrings.NAME, POTION_ID, PotionRarity.PLACEHOLDER, PotionSize.CARD, PotionColor.GREEN);
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
        this.addToBot(new LesserDiscoveryAction(AbstractCard.CardType.SKILL, this.potency));
    }

    public int getPotency(int ascensionLevel) {
        return 1;
    }

    public AbstractPotion makeCopy() {
        return new NanikaSkillPotion();
    }

    static {
        potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    }
}