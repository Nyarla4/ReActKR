package reactkr.orbs.mayo;

import com.megacrit.cardcrawl.orbs.AbstractOrb;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static reactkr.ModFile.makeID;

public class MM_01_SniperBulletOrb extends AbstractBulletOrb {

    public static final String ORB_ID = "SniperBulletOrb";
    public static final String ID = makeID(ORB_ID);
    private static final Logger logger = LogManager.getLogger(MM_01_SniperBulletOrb.class.getName());

    public MM_01_SniperBulletOrb(int amount) {
        super(ORB_ID, "스나이퍼탄",
                amount);
        updateDescription();
    }

    @Override
    public void onEvoke() {

    }

    @Override
    public AbstractOrb makeCopy() {
        return new MM_01_SniperBulletOrb(passiveAmount);
    }

    @Override
    public void updateDescription() {
        this.description = "조준 시 코스트가 1 늘고 피해량이 2배가 됩니다.";
    }
}
