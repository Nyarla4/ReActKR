package reactkr.orbs.mayo;

import com.megacrit.cardcrawl.orbs.AbstractOrb;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static reactkr.ModFile.makeID;

public class MM_04_TCBulletOrb extends AbstractBulletOrb {

    public static final String ORB_ID = "HCBulletOrb";
    public static final String ID = makeID(ORB_ID);
    private static final Logger logger = LogManager.getLogger(MM_04_TCBulletOrb.class.getName());

    public MM_04_TCBulletOrb(int amount) {
        super(ORB_ID, "추공탄",
                amount);
        updateDescription();
    }

    @Override
    public void onEvoke() {

    }

    @Override
    public AbstractOrb makeCopy() {
        return new MM_04_TCBulletOrb(passiveAmount);
    }

    @Override
    public void updateDescription() {
        this.description = "방어도가 있는 적에게는 피해량이 0.5배가 되고 방어도가 없는 적에게는 2배가 됩니다.";
    }
}
