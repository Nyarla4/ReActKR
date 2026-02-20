package reactkr.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.TextAboveCreatureEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;
import reactkr.cards.kuroka.MK_05_ParryMan;
import reactkr.util.MK_05_ParryManager;

public class RelicMsgAction extends AbstractGameAction {
    private final String msg;
    private final Color color;

    public RelicMsgAction(AbstractCreature target, String msg, Color color, float duration) {
        this.target = target;
        this.msg = msg;
        this.color = color;
        this.duration = duration;
        // 핵심: 초기 duration 값을 저장해둡니다.
        this.startDuration = duration;
        this.actionType = ActionType.TEXT;
    }

    @Override
    public void update() {
        // 수정: 현재 남은 시간(this.duration)이 처음 시작 시간(this.startDuration)과 같을 때만 실행
        if (this.duration == this.startDuration) {
            AbstractDungeon.effectList.add(new TextAboveCreatureEffect(
                    target.hb.cX,
                    target.hb.cY + 100.0F * Settings.scale, // 약간 위쪽에 띄우는 게 예쁩니다.
                    msg,
                    color.cpy()
            ));
        }

        this.tickDuration(); // 내부적으로 this.duration을 깎아먹습니다.
    }
}