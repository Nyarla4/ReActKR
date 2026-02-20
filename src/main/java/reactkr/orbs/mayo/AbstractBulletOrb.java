package reactkr.orbs.mayo;

import basemod.abstracts.CustomOrb;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

import static reactkr.ModFile.makeID;
import static reactkr.ModFile.makePath;

public abstract class AbstractBulletOrb extends CustomOrb {

    public AbstractBulletOrb(String ID, String name, int amount) {
        super(makeID(ID), name,
                amount, 0,
                "", "",
                makePath("images/orbs/"+ID+".png"));
        updateDescription();
    }

    @Override
    public abstract void onEvoke();

    @Override
    public abstract AbstractOrb makeCopy();

    @Override
    public abstract void updateDescription();

    @Override
    public void playChannelSFX() {

    }

    @Override
    public void updateAnimation() {
        super.updateAnimation();

        if (this.passiveAmount <= 0) {
            AbstractDungeon.actionManager.addToTop(new EvokeOrbAction(1));
        }
    }
}
