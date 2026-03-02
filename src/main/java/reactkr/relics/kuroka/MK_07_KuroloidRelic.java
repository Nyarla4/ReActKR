package reactkr.relics.kuroka;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerToRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.Kuroka;
import reactkr.powers.kuroka.MK_01_Majinai_Power;
import reactkr.relics.AbstractEasyRelic;

import static reactkr.ModFile.makeID;

public class MK_07_KuroloidRelic extends AbstractEasyRelic {
    public static final String ID = makeID("KuroloidRelic");

    public MK_07_KuroloidRelic() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT, Kuroka.Enums.KUROKA_COLOR);
    }
    private static final Logger logger = LogManager.getLogger(MK_07_KuroloidRelic.class.getName());

    public void atBattleStart() {
        for(AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            this.addToTop(new RelicAboveCreatureAction(m, this));
            m.addPower(new ArtifactPower(m, 1));
        }

        AbstractDungeon.onModifyPower();
    }

    public void onSpawnMonster(AbstractMonster monster) {
        monster.addPower(new ArtifactPower(monster, 1));
        AbstractDungeon.onModifyPower();
    }

    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
    }

    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }
}
