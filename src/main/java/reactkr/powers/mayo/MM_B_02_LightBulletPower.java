package reactkr.powers.mayo;

import com.megacrit.cardcrawl.core.AbstractCreature;

import static reactkr.ModFile.makeID;

public class MM_B_02_LightBulletPower extends AbstractBulletPower {

    public static final String POWER_ID = "LightBulletPower";
    public static final String ID = makeID(POWER_ID);

    public MM_B_02_LightBulletPower(AbstractCreature owner) {
        super(POWER_ID, "경량탄", owner);
        updateDescription();
    }

    @Override
    public void onFire() { }

    @Override
    public void updateDescription() {
        this.description = "조준 시 코스트가 1 줄고 피해량이 0.5배가 됩니다.";
    }
}