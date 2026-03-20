package reactkr.powers.mayo;

import com.megacrit.cardcrawl.core.AbstractCreature;

import static reactkr.ModFile.makeID;

public class MM_B_04_TCBulletPower extends AbstractBulletPower {

    public static final String POWER_ID = "TCBulletPower";
    public static final String ID = makeID(POWER_ID);

    public MM_B_04_TCBulletPower(AbstractCreature owner) {
        super(POWER_ID, "추공탄", owner);
        updateDescription();
    }

    @Override
    public void onFire() { }

    @Override
    public void updateDescription() {
        this.description = "조준이 가능한 공격이 방어도가 있는 적에게는 피해량이 0.5배가 되고 방어도가 없는 적에게는 2배가 됩니다.";
    }
}