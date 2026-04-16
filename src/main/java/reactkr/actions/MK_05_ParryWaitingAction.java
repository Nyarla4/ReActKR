package reactkr.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;
import reactkr.cards.kuroka.MK_05_ParryMan;
import reactkr.util.MK_05_ParryManager;

import static reactkr.util.Wiz.atb;

public class MK_05_ParryWaitingAction extends AbstractGameAction {
    private float waitingTime;
    public MK_05_ParryWaitingAction(AbstractMonster target, float waitingTime) {
        this.target = target;
        this.duration = waitingTime;
        this.waitingTime = waitingTime;
        this.actionType = ActionType.WAIT;
    }

    @Override
    public void update() {
        MK_05_ParryManager.renderDuration = this.duration;

        if (this.duration == this.waitingTime) {
            MK_05_ParryManager.startWindow((AbstractMonster)this.target);
        }

        if (AbstractDungeon.player != null) {
            AbstractDungeon.player.updateInput(); // 마우스 위치 갱신
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                // 마우스가 카드 위에 있고, 왼쪽 버튼이 눌렸다면
                if (c.hb.hovered && com.badlogic.gdx.Gdx.input.isButtonPressed(0)) {
                    if (c instanceof MK_05_ParryMan) { // 패링맨 카드인지 확인
                        // 1. 즉시 효과 발동
                        c.use(AbstractDungeon.player, (AbstractMonster)this.target);
                        // 2. 카드 제거
                            c.exhaust = true;
                            AbstractDungeon.effectsQueue.add(new ExhaustCardEffect(c));
                            c.triggerOnExhaust();
                            AbstractDungeon.player.hand.moveToExhaustPile(c);
                            AbstractDungeon.player.hand.refreshHandLayout();
                        // 3. 성공 처리 및 종료
                        MK_05_ParryManager.parrySuccess = true;
                        this.isDone = true;
                        MK_05_ParryManager.stopWindow();
                        return;
                    }
                }
            }
        }

        this.duration -= com.badlogic.gdx.Gdx.graphics.getDeltaTime();
        if (this.duration <= 0f) {
            MK_05_ParryManager.stopWindow();
            this.isDone = true;
        }
    }
}
