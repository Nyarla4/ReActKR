package reactkr.powers.kuroka;

import com.evacipated.cardcrawl.mod.stslib.patches.core.AbstractCreature.TempHPField;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.powers.AbstractEasyPower;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static reactkr.ModFile.makeID;

public abstract class AbstractRorokaPower extends AbstractEasyPower {
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private static final Logger logger = LogManager.getLogger(AbstractRorokaPower.class.getName());

    public AbstractRorokaPower(String ID, String NAME, boolean isTurnBased, AbstractCreature owner, int amount) {
        super(ID, NAME, PowerType.BUFF, isTurnBased, owner, amount);
    }

    @Override
    public void updateDescription() {
        this.description = "로로카의 최대체력이 " + amount + " 증가합니다.";
    }

    @Override
    public void atStartOfTurn() {
        if(!canUse())
            return;
        additionalEffect();
    }

    private boolean canUse(){
        int curTempHp = TempHPField.tempHp.get(this.owner);
        return curTempHp > 0;
    }

    abstract public void additionalEffect();

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
