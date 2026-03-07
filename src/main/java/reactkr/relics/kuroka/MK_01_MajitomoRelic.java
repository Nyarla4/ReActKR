package reactkr.relics.kuroka;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.Kuroka;
import reactkr.powers.kuroka.MK_01_Majinai_Power;
import reactkr.powers.kuroka.MK_03_DelusionFactor_Power;
import reactkr.relics.AbstractEasyRelic;

import static reactkr.ModFile.makeID;

public class MK_01_MajitomoRelic extends AbstractEasyRelic {
    public static final String ID = makeID("MajitomoRelic");

    public MK_01_MajitomoRelic() {
        super(ID, RelicTier.SPECIAL, LandingSound.FLAT, Kuroka.Enums.KUROKA_COLOR);
    }

    @Override
    public void atBattleStart() {
        this.flash(); // 유물이 반짝이는 시각 효과

        AbstractMonster target = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);

        if (target != null) {
            addToTop(new ApplyPowerAction(target, AbstractDungeon.player,
                    new MK_01_Majinai_Power(target, 1), 1));
        }

        // 전투 시작 시 플레이어에게 망상인자 부여
        addToTop(new ApplyPowerAction(
                AbstractDungeon.player,
                AbstractDungeon.player,
                new MK_03_DelusionFactor_Power(AbstractDungeon.player, 1),
                1
        ));
    }

    @Override
    public void initializeTips(){
        super.initializeTips();

        String keyword = makeID("주문");
        String keyword2 = makeID("망상인자");
        this.tips.add(new PowerTip(BaseMod.getKeywordProper(keyword),
                BaseMod.getKeywordDescription(keyword)));
        this.tips.add(new PowerTip(BaseMod.getKeywordProper(keyword2),
                BaseMod.getKeywordDescription(keyword2)));
    }
}
