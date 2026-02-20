package reactkr.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;

import static reactkr.ModFile.makeID;

public class GainArtifactPower extends AbstractEasyPower {
    // ID 설정 (나중에 텍스트 파일에도 MK_MOD_ID:GainArtifactPower 형태로 등록 필요)
    public static final String POWER_ID = makeID("GainArtifactPower");
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public GainArtifactPower(AbstractCreature owner, int amount) {
        // ID, 이름, 타입, 고정여부(false), 대상, 수량
        super(POWER_ID, NAME, AbstractPower.PowerType.BUFF, false, owner, amount);
        updateDescription();

        AbstractPower artifact = new ArtifactPower(this.owner, this.amount);
        this.region48 = artifact.region48;
        this.region128 = artifact.region128;
    }

    @Override
    public void updateDescription() {
        // DESCRIPTIONS[0] = "이번 턴 종료 시 인공물을 "
        // DESCRIPTIONS[1] = " 얻습니다."
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        // 턴 종료 시 인공물(Artifact) 부여
        this.flash();
        this.addToBot(new ApplyPowerAction(this.owner, this.owner,
                new ArtifactPower(this.owner, this.amount), this.amount));

        // 효과 발동 후 이 파워 제거
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
