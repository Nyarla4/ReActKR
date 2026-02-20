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
import reactkr.actions.AddRorokaHPAction;
import reactkr.powers.kuroka.AbstractRorokaPower;
import reactkr.powers.kuroka.MK_01_Majinai_Power;
import reactkr.powers.kuroka.MK_14_RoroHochi_Power;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static reactkr.ModFile.makeID;
import static reactkr.ModFile.makePath;
import static reactkr.util.Wiz.atb;

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

//    @Override
//    public void onStartOfTurn() {
//        AbstractPlayer p = AbstractDungeon.player;
//
//        if (p.hasPower(MK_14_RoroHochi_Power.POWER_ID)) {
//            AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p,
//                    DamageInfo.createDamageMatrix(3, true),
//                    DamageInfo.DamageType.NORMAL,
//                    AbstractGameAction.AttackEffect.NONE));
//        } else {
//            ArrayList<AbstractMonster> aliveMonsters = new ArrayList<>();
//            for (AbstractMonster mon : AbstractDungeon.getMonsters().monsters) {
//                if (!mon.isDeadOrEscaped()) {
//                    aliveMonsters.add(mon);
//                }
//            }
//            if (!aliveMonsters.isEmpty()) {
//                Collections.shuffle(aliveMonsters, new Random(AbstractDungeon.cardRandomRng.randomLong()));
//                AbstractMonster randomMonster = aliveMonsters.get(0);
//                AbstractDungeon.actionManager.addToBottom(new DamageAction(randomMonster, new DamageInfo(p, 3)));
//            }
//        }
//    }

    @Override
    public AbstractOrb makeCopy() {
        return new MK_00_RorokaOrb();
    }

    @Override
    public void updateDescription() {
        if (TempHPField.tempHp == null || AbstractDungeon.player == null) {
            return;
        }
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
