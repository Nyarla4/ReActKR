package reactkr.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import java.util.ArrayList;

public class PlayRandomCardsFromPoolAction extends AbstractGameAction {
    private boolean exhaustCards;
    private ArrayList<AbstractCard> cardPool;

    // [구조적 변경] 생성자에서 카드풀(ArrayList)을 직접 인자로 받습니다.
    public PlayRandomCardsFromPoolAction(ArrayList<AbstractCard> pool, AbstractCreature target, boolean exhausts) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.WAIT;
        this.source = AbstractDungeon.player;
        this.target = target;
        this.exhaustCards = exhausts;
        this.cardPool = pool;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            // [판단 흐름] 풀이 비어있으면 아무것도 하지 않음
            if (this.cardPool == null || this.cardPool.isEmpty()) {
                this.isDone = true;
                return;
            }

            // [선택 흐름] 인자로 받은 카드풀에서 무작위 카드 1장 선택
            AbstractCard rawCard = this.cardPool.get(AbstractDungeon.cardRandomRng.random(this.cardPool.size() - 1));

            // [구조 보호] 원본 카드풀의 데이터를 보존하기 위해 복제본 생성
            AbstractCard card = rawCard.makeStatEquivalentCopy();

            // [수행 과정] '정제된 혼돈'과 유사한 시각적/로직적 흐름 태우기
            card.exhaustOnUseOnce = this.exhaustCards;

            // 덱에 없는 카드이므로 purgeOnUse를 true로 하면 사용 후 깔끔하게 소멸됩니다.
            // (만약 사용 후 버려진 카드더미로 보내고 싶다면 이 줄을 지우면 됩니다.)
            card.purgeOnUse = true;

            AbstractDungeon.player.limbo.group.add(card);
            card.current_y = -200.0F * Settings.scale;
            card.target_x = (float)Settings.WIDTH / 2.0F + 200.0F * Settings.xScale;
            card.target_y = (float)Settings.HEIGHT / 2.0F;
            card.targetAngle = 0.0F;
            card.lighten(false);
            card.drawScale = 0.12F;
            card.targetDrawScale = 0.75F;

            card.applyPowers();

            // [실행 큐 주입]
            this.addToTop(new NewQueueCardAction(card, this.target, false, true));
            this.addToTop(new UnlimboAction(card));

            if (!Settings.FAST_MODE) {
                this.addToTop(new WaitAction(Settings.ACTION_DUR_MED));
            } else {
                this.addToTop(new WaitAction(Settings.ACTION_DUR_FASTER));
            }

            this.isDone = true;
        }
    }
}