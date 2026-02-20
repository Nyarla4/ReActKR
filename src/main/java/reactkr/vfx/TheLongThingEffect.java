package reactkr.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;

public class TheLongThingEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private static final float DUR = 1.0F;
    private static TextureAtlas.AtlasRegion img;
    private boolean playedSfx = false;
    private boolean flipHorizontal = false;

    public TheLongThingEffect(float x, float y, boolean flipHorizontal) {
        if (img == null) {
            Texture tex = ImageMaster.loadImage("reactkrResources/images/effect/theLongThing.png");

            // 2. [데이터 변환] Texture를 AtlasRegion 구조로 포장
            // AtlasRegion(Texture texture, int x, int y, int width, int height)
            img = new TextureAtlas.AtlasRegion(tex, 0, 0, tex.getWidth(), tex.getHeight());
            //img = ImageMaster.vfxAtlas.findRegion("combat/laserThick");
        }

        this.flipHorizontal = flipHorizontal;
        this.x = x - 125;
        this.y = y - 25;
        this.color = Color.WHITE.cpy();
        this.duration = 1.0F;
        this.startingDuration = 1.0F;
    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();

        // [흐름 제어] 남은 시간에 따라 투명도를 선형적으로 감소 (페이드아웃)
        if (this.duration < 0.0F) {
            this.isDone = true;
        } else {
            // duration이 1.0에서 0으로 갈 때 투명도도 1.0에서 0으로
            this.color.a = this.duration / this.startingDuration;

            // 선택 사항: 살짝 위로 떠오르는 연출을 원한다면
            this.y += Gdx.graphics.getDeltaTime() * 20.0F * Settings.scale;
        }
    }

    public void render(SpriteBatch sb) {
        // 1. [상태 설정] 현재 계산된 투명도가 포함된 색상을 적용
        sb.setColor(this.color);

        // 2. [그리기] 가산 혼합(770, 1)이 필요 없다면 기본값으로 그립니다.
        if (img != null) {
            sb.draw(img, this.x, this.y,
                    (float)img.packedWidth / 2.0F, (float)img.packedHeight / 2.0F,
                    (float)img.packedWidth, (float)img.packedHeight,
                    this.scale, this.scale, this.rotation);
        }
    }

    public void dispose() {
    }
}