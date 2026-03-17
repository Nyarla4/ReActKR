package reactkr.powers.monster;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.powers.watcher.MasterRealityPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.powers.AbstractEasyPower;
import reactkr.powers.kuroka.MK_01_Majinai_Power;

import java.util.function.Supplier;

import static reactkr.ModFile.makeID;

public class OminousMassPower extends AbstractEasyPower {
    public static final String POWER_ID = makeID("OminousMassPower");
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private static final Logger logger = LogManager.getLogger(OminousMassPower.class.getName());

    private final Supplier<AbstractPower>[] debuffPool;

    public OminousMassPower(AbstractCreature owner) {
        super(POWER_ID, NAME, PowerType.DEBUFF, false, owner, -1);
        this.debuffPool = new Supplier[]{
                () -> new StrengthPower(AbstractDungeon.player, -1),//힘
                () -> new DexterityPower(AbstractDungeon.player, -1),//민첩
                () -> new VulnerablePower(AbstractDungeon.player, 1, true),//취약
                () -> new WeakPower(AbstractDungeon.player, 1, true),//약화
                () -> new ConfusionPower(AbstractDungeon.player),//혼란
                () -> new FrailPower(AbstractDungeon.player, 1, true),//손상
                () -> new PoisonPower(AbstractDungeon.player, this.owner, 1),//중독
                () -> new MK_01_Majinai_Power(AbstractDungeon.player, 1),//주문
        };
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public void onDeath() {
        // 난수 처리
        int roll = AbstractDungeon.aiRng.random(0, this.debuffPool.length - 1);

        // 객체 생성
        AbstractPower chosenDebuff = this.debuffPool[roll].get();

        // 객체 적용
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(AbstractDungeon.player, this.owner, chosenDebuff)
        );
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
