package reactkr.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import reactkr.Kuroka;
import reactkr.Latte;
import reactkr.Mayo;

public abstract class AbstractRangerRelic extends AbstractEasyRelic {

    public AbstractRangerRelic(String id) {
        super(id, RelicTier.RARE, LandingSound.FLAT, null);
    }

    // 서브클래스가 각자의 카드를 반환
    protected abstract AbstractCard createCard();

    @Override
    public void onEquip() {
        // 흐름: 카드 획득 이펙트
        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(
                createCard(),
                Settings.WIDTH / 2.0F,
                Settings.HEIGHT / 2.0F
        ));
        // 구조: 풀 초기화 (분리된 역할)
        addOtherRelicsToPool();
    }

    // 풀에 이미 있는지 체크해서 중복 추가 방지 (버그 수정)
    private void addOtherRelicsToPool() {
        if (AbstractDungeon.rareRelicPool == null) return;
        for (String id : RangerRelicConstants.ALL_IDS) {
            if (id.equals(this.relicId)) continue;
            if (AbstractDungeon.player.hasRelic(id)) continue;
            if (AbstractDungeon.rareRelicPool.contains(id)) continue; // ← 버그 수정
            for (int i = 0; i < 3; i++) {
                int idx = AbstractDungeon.relicRng.random(AbstractDungeon.rareRelicPool.size());
                AbstractDungeon.rareRelicPool.add(idx, id);
            }
        }
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.chosenClass.equals(Kuroka.Enums.THE_KUROKA) ||
                AbstractDungeon.player.chosenClass.equals(Mayo.Enums.THE_MAYO)   ||
                AbstractDungeon.player.chosenClass.equals(Latte.Enums.THE_LATTE);
    }
}