package reactkr.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class SlotDigitEffect extends AbstractGameEffect {
    private float x, y;
    private int currentNum; // 현재(보여주는) 숫자
    private int finalNum; // 최종 숫자
    private float rollTimer; // 숫자가 바뀌는 타이머
    private float stopDelay; // 멈추기 전까지 대기하는 시간

    public SlotDigitEffect(float x, float y, int finalNum, float stopDelay) {
        this.x = x;
        this.y = y;
        this.finalNum = finalNum;
        this.stopDelay = stopDelay;
        this.duration = 2.0F; // 최대 연출 시간
        this.rollTimer = 0.05F;
        this.color = Color.WHITE.cpy();
    }

    @Override
    public void update() {
        float dt = Gdx.graphics.getDeltaTime();
        this.stopDelay -= dt;
        this.rollTimer -= dt;

        if (this.stopDelay > 0) {
            // 아직 멈출 때가 아니면 숫자를 계속 바꿈
            if (this.rollTimer < 0) {
                this.currentNum = MathUtils.random(1, 6); // 1~7 사이 무작위 시각적 연출
                this.rollTimer = 0.05F;
            }
        } else {
            // 멈출 시간이 되면 최종 값으로 고정
            this.currentNum = finalNum;
            if (finalNum == 7) this.color = Color.GOLD.cpy(); // 7이면 황금색!

            // 고정된 후 잠시 보여주고 사라짐
            this.duration -= dt;
            if (this.duration < 0) this.isDone = true;
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont,
                Integer.toString(this.currentNum), this.x, this.y, this.color, 1.5F * Settings.scale);
    }


    @Override
    public void dispose() {
    }
}
