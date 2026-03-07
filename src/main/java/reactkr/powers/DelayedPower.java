package reactkr.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static reactkr.ModFile.makeID;

public class DelayedPower extends AbstractPower {
    public static final String POWER_ID = makeID("DelayedPower");
    private AbstractPower powerToApply; // 턴이 끝날 때 적용할 파워 (예: -5 힘)

    private static final Logger logger = LogManager.getLogger(DelayedPower.class.getName());
    private int delayedTurn;
    private boolean isLose;
    private boolean atStart;

    // 파라미터: 소유자, 유지 턴 수, 나중에 실행할 파워 객체
    public DelayedPower(AbstractCreature owner, int turns, AbstractPower powerToApply) {
        this(owner, turns, powerToApply, false);
    }

    public DelayedPower(AbstractCreature owner, int turns, AbstractPower powerToApply, boolean atStart) {
        //나중에 실행할 파워가 양수: 현재는 잃은 상태, 음수: 현재는 얻은 상태
        this.name = powerToApply.name + " " + (powerToApply.amount > 0 ? "상실" : "유지") + " (" + turns + "턴)";
        this.ID = POWER_ID + "_" + powerToApply.ID + "_" + turns; // 여러 종류의 버프가 걸릴 수 있으므로 ID를 고유하게 설정
        this.owner = owner;
        this.delayedTurn = turns;
        this.powerToApply = powerToApply;
        isLose = this.powerToApply.amount < 0;
        this.amount = Math.abs(powerToApply.amount);
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;

        this.atStart = atStart;

        // 아이콘은 나중에 사라질 파워의 아이콘을 그대로 가져와서 씁니다 (직관적)
        this.region48 = powerToApply.region48;
        this.region128 = powerToApply.region128;

        updateDescription();
    }

    @Override
    public void updateDescription() {
        // "3턴 뒤에 힘을 5 잃습니다." 와 같이 표시
        this.description = this.delayedTurn + "턴 후 " + powerToApply.name + "을(를) " + this.amount + (isLose ? " 잃습니다." : " 얻습니다.");
    }

    @Override
    public void atStartOfTurn() {
        if (!atStart)
            return;
        this.delayedTurn--;
        if (this.delayedTurn <= 0) {
            // 저장해둔 마이너스 파워를 실제로 적용
            this.addToBot(new ApplyPowerAction(this.owner, this.owner, powerToApply, this.amount * (isLose ? -1 : 1)));
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        } else {
            updateDescription();
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (atStart)
            return;
        this.delayedTurn--;
        if (this.delayedTurn <= 0) {
            // 저장해둔 마이너스 파워를 실제로 적용
            this.addToBot(new ApplyPowerAction(this.owner, this.owner, powerToApply, this.amount * (isLose ? -1 : 1)));
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        } else {
            updateDescription();
        }
    }
}
