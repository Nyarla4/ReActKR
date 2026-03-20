package reactkr.actions;

import com.evacipated.cardcrawl.mod.stslib.patches.core.AbstractCreature.TempHPField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.HealEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.Kuroka;
import reactkr.orbs.kuroka.MK_00_RorokaOrb;
import reactkr.powers.kuroka.AbstractRorokaPower;

public class AddRorokaHPAction extends AbstractGameAction {
    private static final Logger logger = LogManager.getLogger(AddRorokaHPAction.class.getName());

    public AddRorokaHPAction(AbstractCreature target, AbstractCreature source, int amount) {
        this.setValues(target, source, amount);
        this.actionType = ActionType.HEAL;
    }

    public void update() {
        if (AbstractDungeon.player == null) {
            return;
        }
        if (AbstractDungeon.player instanceof Kuroka) {
            Kuroka p = (Kuroka) AbstractDungeon.player;
            int additionalMaxHealth = 0;
            for (AbstractPower pow : p.powers) {
                if (pow instanceof AbstractRorokaPower) {
                    additionalMaxHealth += pow.amount;
                }
            }

            int healAmount = (Integer) TempHPField.tempHp.get(this.target) + this.amount;
            healAmount = Math.min(healAmount, p.RorokaMaxHp + additionalMaxHealth);
            if (this.duration == 0.5F) {
                TempHPField.tempHp.set(this.target, healAmount);
                if (this.amount > 0) {
                    AbstractDungeon.effectsQueue.add(new HealEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY, this.amount));
                    this.target.healthBarUpdatedEvent();
                }

                if (p.orbs.isEmpty() || p.orbs.get(0) instanceof com.megacrit.cardcrawl.orbs.EmptyOrbSlot) {
                    // [수행 과정] 구체가 없다면 MK_00_RorokaOrb를 생성하여 채널링 흐름에 추가
                    this.addToTop(new ChannelAction(new MK_00_RorokaOrb()));
                }
            }
        } else {
            AbstractPlayer p = AbstractDungeon.player;

            int healAmount = (Integer) TempHPField.tempHp.get(this.target) + this.amount;
            if (this.duration == 0.5F) {
                TempHPField.tempHp.set(this.target, healAmount);
                if (this.amount > 0) {
                    AbstractDungeon.effectsQueue.add(new HealEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY, this.amount));
                    this.target.healthBarUpdatedEvent();
                }

                if (p.orbs.isEmpty() || p.orbs.get(0) instanceof com.megacrit.cardcrawl.orbs.EmptyOrbSlot) {
                    // [수행 과정] 구체가 없다면 MK_00_RorokaOrb를 생성하여 채널링 흐름에 추가
                    this.addToTop(new ChannelAction(new MK_00_RorokaOrb()));
                }
            }
        }

        this.tickDuration();
    }
}
