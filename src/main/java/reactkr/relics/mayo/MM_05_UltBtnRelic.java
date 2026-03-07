package reactkr.relics.mayo;

import basemod.BaseMod;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import reactkr.Mayo;
import reactkr.powers.mayo.MM_01_UltPower;
import reactkr.relics.AbstractEasyRelic;

import static reactkr.ModFile.makeID;

public class MM_05_UltBtnRelic extends AbstractEasyRelic implements ClickableRelic {
    public static final String ID = makeID("UltBtnRelic");
    private boolean usedThisCombat;

    public MM_05_UltBtnRelic() {
        super(ID, RelicTier.COMMON, LandingSound.FLAT, Mayo.Enums.MAYO_COLOR);
    }

    public AbstractRelic makeCopy() {
        return new MM_05_UltBtnRelic();
    }

    @Override
    public void initializeTips() {
        super.initializeTips();

        String keyword = makeID("궁극기");
        this.tips.add(new PowerTip(BaseMod.getKeywordProper(keyword),
                BaseMod.getKeywordDescription(keyword)));
    }

    @Override
    public void onRightClick() {
        // 1. 전투 중인지 확인
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {

            AbstractPlayer p = AbstractDungeon.player;
            // 2. 이번 전투에서 사용하지 않았는지 확인
            if(!this.usedThisCombat){
                if (p.hasPower(MM_01_UltPower.POWER_ID)) {
                    AbstractPower pow = p.getPower(MM_01_UltPower.POWER_ID);
                    if(pow.amount == MM_01_UltPower.maxAmount){
                        AbstractDungeon.effectList.add(new SpeechBubble(
                                p.dialogX, // 플레이어의 말풍선 X 좌표
                                p.dialogY, // 플레이어의 말풍선 Y 좌표
                                2.0f,                           // 말풍선이 화면에 머무는 시간(초)
                                DESCRIPTIONS[2],   // 출력할 텍스트
                                true                            // 플레이어가 말하는 것인가? (true)
                        ));
                        return;
                    }
                }
                // 3. 액션 실행
                this.flash(); // 유물 반짝임
                addToBot(new ApplyPowerAction(p, p, new MM_01_UltPower(p, MM_01_UltPower.maxAmount)));

                // 4. 사용 처리 및 비활성화 연출
                this.usedThisCombat = true;
                this.grayscale = true; // 유물을 회색으로 변경
            }
            else {
                AbstractDungeon.effectList.add(new SpeechBubble(
                        p.dialogX, // 플레이어의 말풍선 X 좌표
                        p.dialogY, // 플레이어의 말풍선 Y 좌표
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
