package reactkr.powers.mayo;

import com.megacrit.cardcrawl.core.AbstractCreature;

import static reactkr.ModFile.makeID;

public class MM_B_01_SniperBulletPower extends AbstractBulletPower {

    public static final String POWER_ID = "SniperBulletPower";
    public static final String ID = makeID(POWER_ID);

    public MM_B_01_SniperBulletPower(AbstractCreature owner) {
        super(POWER_ID, "스나이퍼탄", owner);
        updateDescription();
    }

    @Override
    public void onFire() {
        // 스나이퍼탄 고유 부가효과 (현재 없음)
        // 데미지 배율·코스트 수정은 AbstractAimedCard에서 처리
    }

    @Override
    public void updateDescription() {
        this.description = "조준 시 코스트가 1 늘고 피해량이 2배가 됩니다.";
    }
}