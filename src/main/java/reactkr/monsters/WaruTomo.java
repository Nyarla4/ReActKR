package reactkr.monsters;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import reactkr.powers.monster.OminousMassPower;

import static reactkr.ModFile.makeID;

public class WaruTomo extends AbstractCustomMonster{
    public static final String MonsterID = makeID("wrtm");

    private static final MonsterStrings monsterStrings;
    public static final String NAME;

    private static final byte MOVE_BUFF = 1;
    private static final byte MOVE_WEAK_ATTACK = 2;
    private static final byte MOVE_STRONG_ATTACK = 3;

    private static final int WEAK_ATTACK_PERC = 80;

    public WaruTomo(float x, float y) {
        // 이름, ID, 최대 체력, 히트박스 좌표 및 크기, 이미지 경로 등을 지정합니다.
        super(NAME, MonsterID, 50, -8.0F, 10.0F, 230.0F, 240.0F, "reactkrResources/images/monsters/TempMajitomo.png", x, y);

        // 💡 2. 무기 등록 (구조 세팅)
        this.damage.add(new DamageInfo(this, 4));  // 0번 주머니: 약한 공격
        this.damage.add(new DamageInfo(this, 12)); // 1번 주머니: 강한 공격
    }

    @Override
    protected byte decideMoveByte(int turn, int rngNum) {
        // [구조 결정] 0번째 턴(첫 턴)에는 난수를 무시하고 무조건 버프를 선택합니다.
        if (turn == 0) {
            return MOVE_BUFF;
        }

        // [구조 결정] 1번째 턴부터는 80:20 확률 구조로 분기합니다.
        if (rngNum < WEAK_ATTACK_PERC) { // 0 ~ 79 (80%)
            return MOVE_WEAK_ATTACK;
        } else {                         // 80 ~ 99 (20%)
            return MOVE_STRONG_ATTACK;
        }
    }
    @Override
    protected void setIntentByByte(byte moveByte) {
        // [구조 선언] 결정된 식별자에 맞춰 몬스터 머리 위에 띄울 의도(Intent)를 세팅합니다.
        if (moveByte == MOVE_BUFF) {
            this.setMove(MOVE_BUFF, Intent.BUFF);
        } else if (moveByte == MOVE_WEAK_ATTACK) {
            this.setMove(MOVE_WEAK_ATTACK, Intent.ATTACK, this.damage.get(0).base);
        } else if (moveByte == MOVE_STRONG_ATTACK) {
            // 강한 공격은 단순히 타격만 주므로 Intent.ATTACK을 씁니다. (디버프를 동반한다면 Intent.ATTACK_DEBUFF 사용)
            this.setMove(MOVE_STRONG_ATTACK, Intent.ATTACK, this.damage.get(1).base);
        }
    }

    @Override
    protected void executeTurnFlow(byte moveByte) {
        // [실행 흐름] 결정된 식별자에 맞는 실제 액션을 큐(Queue)에 삽입합니다.
        if (moveByte == MOVE_BUFF) {
            // 첫 턴에 '불길한 덩어리' 파워를 자신에게 부여
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
                    this, this, new OminousMassPower(this)
            ));
        } else if (moveByte == MOVE_WEAK_ATTACK) {
            // 약타
            AbstractDungeon.actionManager.addToBottom(new DamageAction(
                    AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.BLUNT_LIGHT
            ));
        } else if (moveByte == MOVE_STRONG_ATTACK) {
            // 강타
            AbstractDungeon.actionManager.addToBottom(new DamageAction(
                    AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.BLUNT_HEAVY
            ));
        }
    }

    static {
        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(MonsterID);
        NAME = monsterStrings.NAME;
        DIALOG = monsterStrings.DIALOG;
        MOVES = monsterStrings.MOVES;
    }
}
