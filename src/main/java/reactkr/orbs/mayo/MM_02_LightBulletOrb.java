package reactkr.orbs.mayo;

import com.megacrit.cardcrawl.orbs.AbstractOrb;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static reactkr.ModFile.makeID;

public class MM_02_LightBulletOrb extends AbstractBulletOrb {

    public static final String ORB_ID = "LightBulletOrb";
    public static final String ID = makeID(ORB_ID);
    private static final Logger logger = LogManager.getLogger(MM_02_LightBulletOrb.class.getName());

    public MM_02_LightBulletOrb(int amount) {
        super(ORB_ID, "경량탄",
                amount);
        updateDescription();
    }

    @Override
    public void onEvoke() {

    }

    @Override
    public AbstractOrb makeCopy() {
        return new MM_02_LightBulletOrb(passiveAmount);
    }

    @Override
    public void updateDescription() {
        this.description = "조준 시 코스트가 1 줄고 피해량이 0.5배가 됩니다.";
    }
}
