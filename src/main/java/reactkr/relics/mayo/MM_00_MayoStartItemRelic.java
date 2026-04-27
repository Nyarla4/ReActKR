package reactkr.relics.mayo;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.Mayo;
import reactkr.powers.mayo.MM_03_EvasionPower;
import reactkr.relics.AbstractEasyRelic;

import static reactkr.ModFile.makeID;

public class MM_00_MayoStartItemRelic extends AbstractEasyRelic {
    public static final String ID = makeID("MayoStartItemRelic");

    public MM_00_MayoStartItemRelic() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, Mayo.Enums.MAYO_COLOR);
    }

    @Override
    public void atBattleStart(){
        this.flash(); // 유물이 반짝이는 시각 효과

        // 전투 시작 시 플레이어에게 회피 부여
        addToTop(new ApplyPowerAction(
                AbstractDungeon.player,
                AbstractDungeon.player,
                new MM_03_EvasionPower(AbstractDungeon.player, 7),
                1
        ));
    }

    @Override
    public void initializeTips(){
        super.initializeTips();

        String keyword = makeID("회피");
        this.tips.add(new PowerTip(BaseMod.getKeywordProper(keyword),
                BaseMod.getKeywordDescription(keyword)));
    }
}
