package reactkr.potions.nanikaSpecial;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.vfx.combat.ExplosionSmallEffect;

import static reactkr.ModFile.makeID;

public class NanikaExplosivePotion extends AbstractPotion {
    public static String POTION_ID = makeID("NanikaExplosivePotion");
    private static final PotionStrings potionStrings;

    public NanikaExplosivePotion() {
        super(potionStrings.NAME, POTION_ID, PotionRarity.PLACEHOLDER, PotionSize.H, PotionColor.EXPLOSIVE);
        this.isThrown = true;
    }

    public void initializeData() {
        this.potency = this.getPotency();
        this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    public void use(AbstractCreature target) {
        for(AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (!m.isDeadOrEscaped()) {
                this.addToBot(new VFXAction(new ExplosionSmallEffect(m.hb.cX, m.hb.cY), 0.1F));
            }
        }

        this.addToBot(new WaitAction(0.5F));
        this.addToBot(new DamageAllEnemiesAction((AbstractCreature)null, DamageInfo.createDamageMatrix(this.potency, true), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.NONE));
    }

    public int getPotency(int ascensionLevel) {
        return 8;
    }

    public AbstractPotion makeCopy() {
        return new NanikaExplosivePotion();
    }

    static {
        potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    }
}