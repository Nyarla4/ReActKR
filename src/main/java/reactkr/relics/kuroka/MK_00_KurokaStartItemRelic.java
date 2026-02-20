package reactkr.relics.kuroka;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.Kuroka;
import reactkr.powers.kuroka.MK_01_Majinai_Power;
import reactkr.relics.AbstractEasyRelic;

import static reactkr.ModFile.makeID;

public class MK_00_KurokaStartItemRelic extends AbstractEasyRelic {
    public static final String ID = makeID("KurokaStartItemRelic");

    public MK_00_KurokaStartItemRelic() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, Kuroka.Enums.KUROKA_COLOR);
    }

    @Override
    public void atBattleStart() {
        this.flash(); // 유물이 반짝이는 시각 효과

        // 1. 살아있는 몬스터 중 무작위 하나 선택
        AbstractMonster target = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);

        if (target != null) {
            // 2. 선택된 적에게 주문 부여
            // addToTop을 사용하여 전투 시작 시 가장 먼저 처리되도록 합니다.
            addToTop(new ApplyPowerAction(target, AbstractDungeon.player,
                    new MK_01_Majinai_Power(target, 1), 1));
        }
    }

    @Override
    public void initializeTips(){
        super.initializeTips();

        String keyword = makeID("주문");
        this.tips.add(new PowerTip(BaseMod.getKeywordProper(keyword),
                BaseMod.getKeywordDescription(keyword)));
    }
}
