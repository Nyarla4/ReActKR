package reactkr.vfx;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import reactkr.relics.DurianMonsterRelic;

public class MyCampfireRemoveEffect extends AbstractGameEffect {
    private boolean openedScreen = false;
    private DurianMonsterRelic relic;

    public MyCampfireRemoveEffect(DurianMonsterRelic relic) {
        this.relic = relic;
        this.duration = 1.5F;
        // 캠프파이어 뒤의 UI(진행 버튼 등)를 잠시 숨겨서 화면이 꼬이지 않게 보호합니다.
        AbstractDungeon.overlayMenu.proceedButton.hide();
    }

    @Override
    public void update() {
        // 타이머 감소
        if (!AbstractDungeon.isScreenUp) {
            this.duration -= Gdx.graphics.getDeltaTime();
        }

        // 1. [실행 흐름] 처음 업데이트될 때, 덱 목록 창(Grid Select Screen)을 띄웁니다.
        if (!this.openedScreen) {
            this.openedScreen = true;
            CardGroup purgeable = CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck.getPurgeableCards());
            AbstractDungeon.gridSelectScreen.open(purgeable, 1, "두리안에 바칠 카드를 선택하세요.", false, false, false, true);
        }

        // 2. [실행 흐름] 플레이어가 카드를 선택하고 확인을 눌렀을 때의 판정
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {

                // 화면 중앙에 카드가 불타며 사라지는 시각 효과(VFX) 추가
                AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(c, (float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2)));

                // 덱에서 카드를 완전히 삭제
                AbstractDungeon.player.masterDeck.removeCard(c);

                // 🌟 [핵심 구조 갱신] 방금 찢은 카드에 대한 보상으로 유물의 카운터를 올립니다!
                this.relic.incrementCounter();
            }

            // 선택 목록을 비우고, 현재 방(휴식처)의 진행 상태를 완료(COMPLETE)로 밀어버립니다.
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
        }

        // 3. [흐름 종료] 타이머가 다 지나면 이펙트를 끝내고 캠프파이어 화면을 닫습니다.
        if (this.duration < 0.0F) {
            this.isDone = true;
        }
    }

    @Override
    public void render(com.badlogic.gdx.graphics.g2d.SpriteBatch sb) { }
    @Override
    public void dispose() { }
}