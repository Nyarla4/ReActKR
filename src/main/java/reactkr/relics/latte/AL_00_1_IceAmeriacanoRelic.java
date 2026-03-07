package reactkr.relics.latte;

import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import reactkr.Latte;
import reactkr.relics.AbstractEasyRelic;

import static reactkr.ModFile.makeID;

public class AL_00_1_IceAmeriacanoRelic extends AbstractEasyRelic implements ClickableRelic {
    public static final String ID = makeID("IceAmeriacanoRelic");
    private static final int healAmount = 5;
    private boolean usedThisCombat = false;

    public AL_00_1_IceAmeriacanoRelic() {
        super(ID, RelicTier.SPECIAL, LandingSound.FLAT, Latte.Enums.LATTE_COLOR);
    }

    @Override
    public void onRightClick() {
        // 1. 전투 중인지 확인
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {

            // 2. 이번 전투에서 사용하지 않았는지 확인
            if(!this.usedThisCombat){
                // 3. 회복 액션 실행
                this.flash(); // 유물 반짝임
                addToBot(new HealAction(AbstractDungeon.player, AbstractDungeon.player, healAmount));

                // 4. 사용 처리 및 비활성화 연출
                this.usedThisCombat = true;
                this.grayscale = true; // 유물을 회색으로 변경
            }
            else {
                AbstractDungeon.effectList.add(new SpeechBubble(
                        AbstractDungeon.player.dialogX, // 플레이어의 말풍선 X 좌표
                        AbstractDungeon.player.dialogY, // 플레이어의 말풍선 Y 좌표
                        2.0f,                           // 말풍선이 화면에 머무는 시간(초)
                        DESCRIPTIONS[1],   // 출력할 텍스트
                        true                            // 플레이어가 말하는 것인가? (true)
                ));
            }
        }
    }

    @Override
    public void atPreBattle() {
        // 전투 시작 시 초기화
        this.usedThisCombat = false;
        this.grayscale = false;
    }

    @Override
    public void onVictory() {
        // 전투 승리 시 회색 해제
        this.grayscale = false;
    }
}
