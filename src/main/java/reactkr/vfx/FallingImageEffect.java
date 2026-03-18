package reactkr.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ExplosionSmallEffect; // 부딪쳤을 때 터지는 이펙트

public class FallingImageEffect extends AbstractGameEffect {
    // 💡 [구조 변수]
    private Texture img;             // 떨어질 이미지 본체
    private float x, y;              // 현재 좌표
    private float destX, destY;      // 도달할 타겟의 좌표 (hb.cX, hb.cY)
    private float vY;                // 수직 낙하 속도
    private float startingY;         // 생성된 시작 높이 (화면 위쪽)
    private boolean impacted;        // 타겟에 부딪쳤는지 여부

    private boolean explode;

    public FallingImageEffect(float destX, float destY, Texture img) {
        this(destX, destY, img, false);
    }

    // [구조 생성자] 타겟 좌표(destX, destY)와 떨어질 이미지(Texture)를 외부에서 받습니다.
    public FallingImageEffect(float destX, float destY, Texture img, boolean explode) {
        this.img = img;
        this.destX = destX;
        this.destY = destY;
        this.explode = explode;

        // 🌟 [생성 흐름] 애니메이션 초기화
        this.duration = 2.0F;        // 이펙트 총 지속 시간
        this.startingY = (float) Settings.HEIGHT + (float) img.getHeight() * Settings.scale; // 화면 맨 위에서 생성
        this.y = this.startingY;
        this.x = destX;              // x 좌표는 타겟과 동일하게 고정
        this.vY = 0.0F;              // 처음 낙하 속도는 0
        this.impacted = false;       // 아직 부딪치지 않음

        // 색상 및 회전 등 기타 구조적 초기화
        this.color = new Color(1.0F, 1.0F, 1.0F, 1.0F); // 불투명
        this.rotation = MathUtils.random(-10.0F, 10.0F); // 약간 기울어서 떨어지게
        this.scale = Settings.scale;
    }

    // 🌟 [실행 흐름] 매 프레임마다 애니메이션(낙하)을 처리합니다.
    @Override
    public void update() {
        // [흐름]: 가속도(중력)를 적용하여 낙하 속도를 늘립니다.
        this.vY += 2000.0F * Settings.scale * Gdx.graphics.getDeltaTime();
        this.y -= this.vY * Gdx.graphics.getDeltaTime();

        // [흐름]: 타겟의 y 좌표에 도달했는지 확인합니다.
        if (this.y < this.destY && !this.impacted) {
            this.impacted = true; // 부딪침 판정

            // 💡 [흐름 제어]: 부딪쳤을 때 사운드와 추가 VFX를 큐에 넣습니다.
            CardCrawlGame.sound.play("BLUNT_HEAVY"); // 무거운 타격음
            if (explode) {
                AbstractDungeon.effectsQueue.add(new ExplosionSmallEffect(this.destX, this.destY)); // 팡!
            }

            // [흐름]: 본체 애니메이션 종료 처리
            this.isDone = true;
        }

        // [흐름]: 타이머 감소
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }
    }

    // 💡 [렌더링 구조] 매 프레임마다 이미지를 화면에 그립니다.
    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(this.color);
        // [구조]: 정의해둔 좌표, 회전값, 크기 등을 바탕으로 이미지를 화면에 렌더링합니다.
        sb.draw(this.img, this.x - (float) this.img.getWidth() / 2.0F, this.y - (float) this.img.getHeight() / 2.0F, (float) this.img.getWidth() / 2.0F, (float) this.img.getHeight() / 2.0F, (float) this.img.getWidth(), (float) this.img.getHeight(), this.scale, this.scale, this.rotation, 0, 0, this.img.getWidth(), this.img.getHeight(), false, false);
    }

    @Override
    public void dispose() {
        // 텍스트 이미지는 여기서 dispose하지 않습니다 (전역 리소스로 관리할 것이기 때문).
    }
}