package reactkr.orbs.mayo;

import basemod.abstracts.CustomOrb;
import com.evacipated.cardcrawl.mod.stslib.patches.core.AbstractCreature.TempHPField;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import reactkr.stances.mayo.ApexStance;
import reactkr.stances.mayo.OverwatchStance;

import static reactkr.ModFile.makeID;
import static reactkr.ModFile.makePath;

public abstract class AbstractBulletOrb extends CustomOrb {

    public AbstractBulletOrb(String ID, String name, int amount) {
        super(makeID(ID), name,
                amount, 0,
                "", "",
                makePath("images/orbs/"+ID+".png"));
        AbstractDungeon.actionManager.addToBottom(new ChangeStanceAction(new ApexStance()));
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
