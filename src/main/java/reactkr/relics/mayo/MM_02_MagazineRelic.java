package reactkr.relics.mayo;

import basemod.BaseMod;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import reactkr.Kuroka;
import reactkr.Latte;
import reactkr.Mayo;
import reactkr.actions.SelectAndResetMagicNumberAction;
import reactkr.cards.mayo.AbstractAimedCard;
import reactkr.powers.mayo.MM_01_UltPower;
import reactkr.relics.AbstractEasyRelic;

import static reactkr.ModFile.makeID;

public class MM_02_MagazineRelic extends AbstractEasyRelic implements ClickableRelic {
    public static final String ID = makeID("MagazineRelic");
    private boolean usedThisCombat = false;

    public MM_02_MagazineRelic() {
        super(ID, RelicTier.COMMON, LandingSound.FLAT, Mayo.Enums.MAYO_COLOR);
    }

    public AbstractRelic makeCopy() {
        return new MM_02_MagazineRelic();
    }

    @Override
    public void onRightClick() {
        // 1. 전투 중인지 확인
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {

            // 2. 이번 전투에서 사용하지 않았는지 확인
            if(!this.usedThisCombat){
                AbstractPlayer p = AbstractDungeon.player;

                boolean hasValidCard = p.hand.group.stream()
                        .filter(f -> f instanceof AbstractAimedCard)         // 1. 타입 필터
                        .map(f -> (AbstractAimedCard) f)                     // 2. 형변환
                        .anyMatch(ac -> ac.basicDepletion() > -1 &&
                                ac.baseMagicNumber < ac.basicDepletion());        // 3. 조건 확인
                if (!hasValidCard) {
                    // 1. [시각적 흐름] 플레이어 머리 위에 말풍선 생성
                    // SpeechBubble(x좌표, y좌표, 지속시간, 내용, 플레이어 대사 여부)
                    AbstractDungeon.effectList.add(new SpeechBubble(
                            AbstractDungeon.player.hb.cX,
                            AbstractDungeon.player.hb.cY,
                            2.0F, // 2초 동안 표시
                            "고갈을 회복할 카드가 없어.",
                            true // 플레이어의 대사(말풍선 꼬리가 플레이어를 향함)
                    ));

                    // 2. [청각적 흐름] 선택 실패를 알리는 가벼운 효과음 (선택 사항)
                    CardCrawlGame.sound.play("UI_CLICK_1");
                    return;
                }

                // 2. 액션 실행
                this.flash(); // 유물 반짝임
                AbstractDungeon.actionManager.addToBottom(new SelectAndResetMagicNumberAction());
                // 3. 사용 처리 및 비활성화 연출
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

    @Override
    public void initializeTips() {
        super.initializeTips();

        String keyword = makeID("고갈");
        this.tips.add(new PowerTip(BaseMod.getKeywordProper(keyword),
                BaseMod.getKeywordDescription(keyword)));
    }
}
