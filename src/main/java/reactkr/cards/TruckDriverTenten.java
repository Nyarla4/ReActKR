package reactkr.cards;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.ModFile;
import reactkr.vfx.FallingImageEffect;

import static reactkr.ModFile.makeID;

public class TruckDriverTenten extends AbstractEasyCard {
    public final static String ID = makeID("TruckDriverTenten");
    private final Texture TRUCK_IMG;

    public TruckDriverTenten() {
        super(ID, 2, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ALL_ENEMY, CardColor.COLORLESS);
        baseDamage = damage = 24;
        TRUCK_IMG = ImageMaster.loadImage("reactkrResources/images/effect/truck.png");
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        float centerX = Settings.WIDTH * 0.75F;
        float centerY = AbstractDungeon.floorY + (150.0F * Settings.scale);
        addToBot(new VFXAction(new FallingImageEffect(centerX, centerY, TRUCK_IMG, true), 1.0F));
        this.addToBot(new DamageAllEnemiesAction(
                p,
                this.multiDamage,
                this.damageTypeForTurn,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY
        ));
    }

    @Override
    public void upp() {
    }

}