package reactkr.vfx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import reactkr.ModFile;

public class MajinaiFlashAtkImgEffect extends AbstractGameEffect {
    public TextureAtlas.AtlasRegion img;
    private float x;
    private float y;
    private static final float DURATION = 0.6F;

    public MajinaiFlashAtkImgEffect(float x, float y) {
        this.duration = 0.6F;
        this.startingDuration = 0.6F;
        this.img = this.loadImage();
        if (this.img != null) {
            this.x = x - (float)this.img.packedWidth / 2.0F;
            y -= (float)this.img.packedHeight / 2.0F;
        }

        this.color = new Color(0.6f, 0.3f, 0.8f, 1f);//ModFile.kurokaColor;
        this.scale = Settings.scale;

        this.y = y;
    }

    private TextureAtlas.AtlasRegion loadImage() {
        return ImageMaster.ATK_POISON;
    }

    private void playSound(AbstractGameAction.AttackEffect effect) {
        CardCrawlGame.sound.play("ATTACK_POISON");
    }

    public void update() {
        super.update();
    }

    public void render(SpriteBatch sb) {
        if (this.img != null) {
            sb.setColor(this.color);
            sb.draw(this.img, this.x, this.y, (float)this.img.packedWidth / 2.0F, (float)this.img.packedHeight / 2.0F, (float)this.img.packedWidth, (float)this.img.packedHeight, this.scale, this.scale, this.rotation);
        }

    }

    public void dispose() {
    }
}
