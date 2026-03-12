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
}
