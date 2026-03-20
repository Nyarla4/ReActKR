package reactkr.orbs.kuroka;

import basemod.abstracts.CustomOrb;
import com.evacipated.cardcrawl.mod.stslib.patches.core.AbstractCreature.TempHPField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.Kuroka;
import reactkr.powers.kuroka.AbstractRorokaPower;

import static reactkr.ModFile.makeID;
import static reactkr.ModFile.makePath;

public class MK_00_RorokaOrb extends CustomOrb {

    public static final String ORB_ID = makeID("RorokaOrb");
    private static final Logger logger = LogManager.getLogger(MK_00_RorokaOrb.class.getName());

    public MK_00_RorokaOrb() {
        super(ORB_ID, "로로카",
                0, 0,
                "로로카 방어중!", "",
                makePath("images/orbs/RoroHiOrb.png"));
        updateDescription();
    }

    @Override
    public void onEvoke() {

    }

    @Override
    public AbstractOrb makeCopy() {
        return new MK_00_RorokaOrb();
    }

    @Override
    public void updateDescription() {
        if (TempHPField.tempHp == null || AbstractDungeon.player == null) {
            return;
        }
        if(AbstractDungeon.player instanceof Kuroka) {
            Kuroka p = (Kuroka) AbstractDungeon.player;
            int additionalMaxHealth = 0;
            for (AbstractPower pow : p.powers){
                if(pow instanceof AbstractRorokaPower){
                    additionalMaxHealth += pow.amount;
                }
            }

            int curHp = TempHPField.tempHp.get(AbstractDungeon.player);
            this.description = "로로카가 대신해서 피해를 받습니다. 남은 체력: #b" + curHp + "/" + (p.RorokaMaxHp + additionalMaxHealth);
        }
        else {
            int curHp = TempHPField.tempHp.get(AbstractDungeon.player);
            this.description = "로로카가 대신해서 피해를 받습니다. 남은 체력: #b" + curHp;
        }
    }

    @Override
    public void playChannelSFX() {

    }

    @Override
    public void updateAnimation() {
        super.updateAnimation();
        AbstractPlayer p = AbstractDungeon.player;
        if (p != null) {
            this.passiveAmount = TempHPField.tempHp.get(p);
            updateDescription();
        }
        if (this.passiveAmount <= 0) {
            AbstractDungeon.actionManager.addToTop(new EvokeOrbAction(1));
        }
    }
}
