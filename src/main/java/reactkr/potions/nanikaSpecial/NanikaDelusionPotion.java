package reactkr.potions.nanikaSpecial;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import reactkr.powers.kuroka.MK_03_DelusionFactor_Power;

import static reactkr.ModFile.makeID;

public class NanikaDelusionPotion extends AbstractPotion {
    public static String POTION_ID = makeID("NanikaDelusionPotion");
    private static final PotionStrings potionStrings;

    public NanikaDelusionPotion() {
        super(potionStrings.NAME, POTION_ID, PotionRarity.PLACEHOLDER, PotionSize.SPHERE, PotionEffect.RAINBOW, Color.WHITE, (Color)null, (Color)null);
        this.isThrown = false;
    }

    public void initializeData() {
        this.potency = this.getPotency();
        this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(TipHelper.capitalize("망상인자"), GameDictionary.keywords.get(makeID("망상인자"))));
    }

    public void use(AbstractCreature target) {
        AbstractCreature var2 = AbstractDungeon.player;
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            this.addToBot(new ApplyPowerAction(var2, AbstractDungeon.player, new MK_03_DelusionFactor_Power(var2, this.potency), this.potency));
        }
    }

    public int getPotency(int ascensionLevel) {
        return 2;
    }

    public AbstractPotion makeCopy() {
        return new NanikaDelusionPotion();
    }

    static {
        potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    }
}