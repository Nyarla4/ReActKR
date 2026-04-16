package reactkr.relics.mayo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import reactkr.Mayo;
import reactkr.powers.mayo.MM_03_EvasionPower;
import reactkr.powers.mayo.MM_12_ExcitedPower;
import reactkr.relics.AbstractEasyRelic;

import static reactkr.ModFile.makeID;

public class MM_07_MayoUpgradeItemRelic extends AbstractEasyRelic {
    public static final String ID = makeID("MayoUpgradeItemRelic");

    public MM_07_MayoUpgradeItemRelic() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT, Mayo.Enums.MAYO_COLOR);
    }

    @Override
    public void atBattleStart(){
        this.flash(); // 유물이 반짝이는 시각 효과

        // 전투 시작 시 플레이어에게 영구 회피 부여
        addToTop(new ApplyPowerAction(
                AbstractDungeon.player,
                AbstractDungeon.player,
                new MM_03_EvasionPower(AbstractDungeon.player, 7),
                0
        ));
        addToTop(new ApplyPowerAction(
                AbstractDungeon.player,
                AbstractDungeon.player,
                new MM_12_ExcitedPower(AbstractDungeon.player, 7),
                0
        ));
    }

    @Override
    public void obtain(){
        // 플레이어가 대체할 시작 유물을 가지고 있는지 확인합니다.
        if (AbstractDungeon.player.hasRelic(MM_00_MayoStartItemRelic.ID)) {

            // 가지고 있다면, 플레이어의 유물 인벤토리 구조를 순회하여 몇 번째 슬롯인지 찾습니다.
            for (int i = 0; i < AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(MM_00_MayoStartItemRelic.ID)) {

                    // 핵심 메서드: 기존 유물을 지우고, 나 자신(this)을 i번째 슬롯에 끼워 넣습니다.
                    this.instantObtain(AbstractDungeon.player, i, true);
                    break; // 교체했으니 반복문 종료
                }
            }
        } else {
            // 만약 플레이어가 니오우 이벤트 등으로 이미 시작 유물을 잃어버린 상태라면,
            // 대체하지 않고 맨 뒷자리에 평범하게 추가되도록 부모의 획득 흐름을 따릅니다.
            super.obtain();
        }
    }
}
