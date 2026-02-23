package reactkr.orbs.mayo;

import com.megacrit.cardcrawl.orbs.AbstractOrb;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static reactkr.ModFile.makeID;

public class MM_03_HPBulletOrb extends AbstractBulletOrb {

    public static final String ORB_ID = "HPBulletOrb";
    public static final String ID = makeID(ORB_ID);
    private static final Logger logger = LogManager.getLogger(MM_03_HPBulletOrb.class.getName());

    public MM_03_HPBulletOrb(int amount) {
        super(ORB_ID, "분쇄탄",
                amount);
        updateDescription();
    }

    @Override
    public void onEvoke() {

    }

    @Override
    public AbstractOrb makeCopy() {
        return new MM_03_HPBulletOrb(passiveAmount);
    }

    @Override
    public void updateDescription() {
        this.description = "방어도가 없는 적에게는 피해량이 0.5배가 되고 방어도가 있는 적에게는 2배가 됩니다.";
    }
}
