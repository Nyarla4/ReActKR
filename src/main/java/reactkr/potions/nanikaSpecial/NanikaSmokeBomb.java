package reactkr.potions.nanikaSpecial;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.unique.RandomizeHandCostAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.combat.SmokeBombEffect;

import static reactkr.ModFile.makeID;

public class NanikaSmokeBomb extends AbstractPotion {
    public static String POTION_ID = makeID("NanikaSmokeBomb");
    private static final PotionStrings potionStrings;

    public NanikaSmokeBomb() {
        super(potionStrings.NAME, POTION_ID, PotionRarity.PLACEHOLDER, PotionSize.SPHERE, PotionColor.SMOKE);
        this.description = potionStrings.DESCRIPTIONS[0];
        this.isThrown = true;
        this.tips.add(new PowerTip(this.name, this.description));
    }

    public void use(AbstractCreature target) {
        AbstractCreature var2 = AbstractDungeon.player;
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            AbstractDungeon.getCurrRoom().smoked = true;
            this.addToBot(new VFXAction(new SmokeBombEffect(var2.hb.cX, var2.hb.cY)));
            AbstractDungeon.player.hideHealthBar();
            AbstractDungeon.player.isEscaping = true;
            AbstractDungeon.player.flipHorizontal = !AbstractDungeon.player.flipHorizontal;
            AbstractDungeon.overlayMenu.endTurnButton.disable();
            AbstractDungeon.player.escapeTimer = 2.5F;
        }

    }

    public boolean canUse() {
        if (super.canUse()) {
            for(AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (m.hasPower("BackAttack")) {
                    return false;
                }

                if (m.type == AbstractMonster.EnemyType.BOSS) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public int getPotency(int ascensionLevel) {
        return 0;
    }

    public AbstractPotion makeCopy() {
        return new NanikaSmokeBomb();
    }

    static {
        potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    }
}