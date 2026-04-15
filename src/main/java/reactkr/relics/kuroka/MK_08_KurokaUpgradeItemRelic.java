package reactkr.relics.kuroka;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import reactkr.Kuroka;
import reactkr.powers.kuroka.MK_01_Majinai_Power;
import reactkr.relics.AbstractEasyRelic;

import static reactkr.ModFile.makeID;

public class MK_08_KurokaUpgradeItemRelic extends AbstractEasyRelic {
    public static final String ID = makeID("KurokaUpgradeItemRelic");

    public MK_08_KurokaUpgradeItemRelic() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT, Kuroka.Enums.KUROKA_COLOR);
    }

    @Override
    public void atBattleStart() {
        this.flash(); // 유물이 반짝이는 시각 효과

        AbstractPlayer p = AbstractDungeon.player;
        for(AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            addToBot(new ApplyPowerAction(m, p, new MK_01_Majinai_Power(m, 1), 1));
        }

        this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }

    @Override
    public void initializeTips(){
        super.initializeTips();

        String keyword = makeID("주문");
        this.tips.add(new PowerTip(BaseMod.getKeywordProper(keyword),
                BaseMod.getKeywordDescription(keyword)));
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(MK_00_KurokaStartItemRelic.ID);
    }

    @Override
    public void obtain(){
        // 플레이어가 대체할 시작 유물을 가지고 있는지 확인합니다.
        if (AbstractDungeon.player.hasRelic(MK_00_KurokaStartItemRelic.ID)) {

            // 가지고 있다면, 플레이어의 유물 인벤토리 구조를 순회하여 몇 번째 슬롯인지 찾습니다.
            for (int i = 0; i < AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(MK_00_KurokaStartItemRelic.ID)) {

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
