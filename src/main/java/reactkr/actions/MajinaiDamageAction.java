package reactkr.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import reactkr.vfx.MajinaiFlashAtkImgEffect;

public class MajinaiDamageAction extends AbstractGameAction {
    private DamageInfo info;
    private static final float DURATION = 0.1F;
    private static final float POST_ATTACK_WAIT_DUR = 0.1F;
    private boolean skipWait;

    public MajinaiDamageAction(AbstractCreature target, DamageInfo info) {
        this.skipWait = false;
        this.info = info;
        this.setValues(target, info);
        this.actionType = ActionType.DAMAGE;
        this.duration = 0.1F;
    }

    public void update() {
        if (this.shouldCancelAction() && this.info.type != DamageInfo.DamageType.THORNS) {
            this.isDone = true;
        } else {
            if (this.duration == 0.1F) {
                if (this.info.type != DamageInfo.DamageType.THORNS && (this.info.owner.isDying || this.info.owner.halfDead)) {
                    this.isDone = true;
                    return;
                }

                AbstractDungeon.effectList.add(new MajinaiFlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY));
            }

            this.tickDuration();
            if (this.isDone) {
                this.target.tint.color.set(Color.CHARTREUSE.cpy());
                this.target.tint.changeColor(Color.WHITE.cpy());

                this.target.damage(this.info);
                if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                    AbstractDungeon.actionManager.clearPostCombatActions();
                }

                if (!this.skipWait && !Settings.FAST_MODE) {
                    this.addToTop(new WaitAction(0.1F));
                }
            }

        }
    }
}