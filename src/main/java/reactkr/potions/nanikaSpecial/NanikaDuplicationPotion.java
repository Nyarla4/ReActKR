package reactkr.potions.nanikaSpecial;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.DuplicationPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static reactkr.ModFile.makeID;

public class NanikaDuplicationPotion extends AbstractPotion {
    public static String POTION_ID = makeID("NanikaDuplicationPotion");
    private static final PotionStrings potionStrings;

    public NanikaDuplicationPotion() {
        super(potionStrings.NAME, POTION_ID, PotionRarity.PLACEHOLDER, PotionSize.CARD, PotionEffect.RAINBOW, Color.WHITE, (Color)null, (Color)null);
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
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DuplicationPower(AbstractDungeon.player, this.potency), this.potency));
        }

    }

    public int getPotency(int ascensionLevel) {
        return 1;
    }

    public AbstractPotion makeCopy() {
        return new NanikaDuplicationPotion();
    }

    static {
        potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    }
}