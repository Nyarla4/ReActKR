package reactkr.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import reactkr.vfx.SlotDigitEffect;

public class TripleSlotAction extends AbstractGameAction {
    private int[] results;
    private boolean vfxStarted = false;

    public TripleSlotAction(int[] results) {
        this.results = results;
        // 이 duration 동안 게임의 다음 addToBot 액션들은 대기 상태
        this.duration = 2.0F;
        this.actionType = AbstractGameAction.ActionType.WAIT;
    }

    @Override
    public void update() {
        if (!vfxStarted) {
            // 연출 시작
            float centerX = Settings.WIDTH / 2.0F;
            float centerY = Settings.HEIGHT / 4.0F * 3.0F;
            AbstractDungeon.effectsQueue.add(new SlotDigitEffect(centerX - 100f, centerY, results[0], 0.5F));
            AbstractDungeon.effectsQueue.add(new SlotDigitEffect(centerX, centerY, results[1], 1.0F));
            AbstractDungeon.effectsQueue.add(new SlotDigitEffect(centerX + 100f, centerY, results[2], 1.5F));
            vfxStarted = true;
        }

        // duration을 프레임 단위
        this.tickDuration();
    }
}