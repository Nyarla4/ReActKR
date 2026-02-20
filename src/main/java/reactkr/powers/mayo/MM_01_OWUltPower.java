package reactkr.powers.mayo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.powers.AbstractEasyPower;

import static reactkr.ModFile.makeID;

public class MM_01_OWUltPower extends AbstractEasyPower {

    public static final String POWER_ID = makeID("OWUltPower");
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private static final Logger logger = LogManager.getLogger(MM_01_OWUltPower.class.getName());
    public static final int maxAmount = 10;

    public MM_01_OWUltPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (amount >= maxAmount)
            return;
        if (isPlayer) {
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(this.owner, this.owner, new MM_01_OWUltPower(this.owner, 1), 1)
            );
        }
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (amount >= maxAmount)
            return;
        // 흐름: 내가 공격을 완료한 시점
        if (damageAmount > 0 && target != this.owner && info.type == DamageInfo.DamageType.NORMAL) {
            this.flash();
            // 구조 변경: 울트 게이지 파워 추가
            this.addToBot(new ApplyPowerAction(this.owner, this.owner, new MM_01_OWUltPower(this.owner, 1), 1));
        }
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (amount >= maxAmount)
            return damageAmount;
        // 흐름: 실제로 데미지 판정이 일어나는 시점 (atDamageReceive와 달리 한 번만 실행됨)
        if (info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS) {
            this.flash();
            // 구조 변경: 울트 게이지 파워 추가
            this.addToTop(new ApplyPowerAction(this.owner, this.owner, new MM_01_OWUltPower(this.owner, 1), 1));
        }
        return damageAmount; // 중요: 받은 데미지를 그대로 반환해야 구조가 깨지지 않음
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
        if (this.amount < maxAmount) {
            this.description += DESCRIPTIONS[1] + this.amount + "/" + maxAmount;
        } else {
            this.description += DESCRIPTIONS[2] + " :" + maxAmount;
        }
    }

    @Override
    public void stackPower(int amount) {
        if (this.amount > maxAmount - 1) {
            return;
        }
        super.stackPower(amount);
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}