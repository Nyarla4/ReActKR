package reactkr.powers.kuroka;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.powers.AbstractEasyPower;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.atb;

public class MK_09_SecomMasada_Power extends AbstractEasyPower {
    public static final String POWER_ID = makeID("SecomMasadaPower");
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private static final Logger logger = LogManager.getLogger(MK_09_SecomMasada_Power.class.getName());

    private final int magicNumber;
    private final int secondMagic;
    private int delusion;
    private boolean witchificated;

    public MK_09_SecomMasada_Power(AbstractCreature owner, int magicNumber, int secondMagic) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, 1);
        this.magicNumber = magicNumber;
        this.secondMagic = secondMagic;
        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        int damage = delusion * (witchificated ? secondMagic : magicNumber);
        if (damage > 0) {
            flash(); // 파워 시각 효과

            atb(new DamageAllEnemiesAction(owner,
                    DamageInfo.createDamageMatrix(damage, true),
                    DamageInfo.DamageType.THORNS,
                    AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
    }

    @Override
    public void update(int slot) {
        super.update(slot);
        boolean curWitchificated = this.owner.hasPower(MK_04_Witchification_Power.POWER_ID);
        if (curWitchificated != witchificated) {
            witchificated = curWitchificated;
            updateDescription();
        }
        if (this.owner.hasPower(MK_03_DelusionFactor_Power.POWER_ID)) {
            AbstractPower delusion = this.owner.getPower(MK_03_DelusionFactor_Power.POWER_ID);
            int curDelusion = delusion.amount;

            if (this.delusion != curDelusion) {
                this.delusion = curDelusion;
                updateDescription();
            }
        }
    }

    @Override
    public void updateDescription() {
        int damage = delusion * (witchificated ? secondMagic : magicNumber);
        description = DESCRIPTIONS[0] + damage + DESCRIPTIONS[1];
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
