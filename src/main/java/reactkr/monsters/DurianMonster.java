package reactkr.monsters;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.powers.FrailPower;
import reactkr.powers.DurianMonsterPower;
import reactkr.powers.monster.OminousMassPower;

import static reactkr.ModFile.makeID;

public class DurianMonster extends AbstractCustomMonster{
    public static final String MonsterID = makeID("DurianMonster");

    private static final MonsterStrings monsterStrings;
    public static final String NAME;

    private static final byte MOVE_BUFF = 1;
    private static final byte MOVE_WEAK_ATTACK = 2;
    private static final byte MOVE_MED_ATTACK = 3;  // 새로 추가된 중타(3연타)
    private static final byte MOVE_STRONG_ATTACK = 4;

    public DurianMonster(float x, float y) {
        // 이름, ID, 최대 체력, 히트박스 좌표 및 크기, 이미지 경로 등을 지정합니다.
        super(NAME, MonsterID, 50, -8.0F, 10.0F, 230.0F, 240.0F, "reactkrResources/images/monsters/TempMajitomo.png", x, y);

        this.damage.add(new DamageInfo(this, 6));  // 0번 주머니: 약타 (6)
        this.damage.add(new DamageInfo(this, 8));  // 1번 주머니: 중타 (8)
        this.damage.add(new DamageInfo(this, 12)); // 2번 주머니: 강타 (12)
    }

    @Override
    public void usePreBattleAction() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
                this, this, new DurianMonsterPower(this)
        ));
    }

    @Override
    protected byte decideMoveByte(int turn, int rngNum) {
        if (turn == 0) {
            return MOVE_WEAK_ATTACK;
        }
        if (rngNum < 40) {               // 40% 확률로 약타
            if (this.lastMove(MOVE_WEAK_ATTACK)) { // 슬더스 내장 helper 메서드 활용
                if (AbstractDungeon.aiRng.randomBoolean()) {
                    return MOVE_MED_ATTACK;
                } else {
                    return MOVE_STRONG_ATTACK;
                }
            }
            return MOVE_WEAK_ATTACK;
        } else if (rngNum < 80) {        // 40% 확률로 중타
            if (this.lastMove(MOVE_MED_ATTACK)) {
                if (AbstractDungeon.aiRng.randomBoolean()) {
                    return MOVE_WEAK_ATTACK;
                } else {
                    return MOVE_STRONG_ATTACK;
                }
            }
            return MOVE_MED_ATTACK;
        } else {                         // 20% 확률로 강타
            if (this.lastMove(MOVE_STRONG_ATTACK)) {
                if (AbstractDungeon.aiRng.randomBoolean()) {
                    return MOVE_WEAK_ATTACK;
                } else {
                    return MOVE_MED_ATTACK;
                }
            }
            return MOVE_STRONG_ATTACK;
        }
    }

    @Override
    protected void setIntentByByte(byte moveByte) {
        if (moveByte == MOVE_WEAK_ATTACK) {
            // 약타: 데미지와 함께 디버프를 주므로 Intent.ATTACK_DEBUFF 사용
            this.setMove(MOVE_WEAK_ATTACK, Intent.ATTACK_DEBUFF, this.damage.get(0).base);
        } else if (moveByte == MOVE_MED_ATTACK) {
            // 🌟 중타: 1번 주머니(8 데미지)를 꺼내고, 3번(연타 횟수) 때리며, 연타여부(true)를 명시합니다.
            this.setMove(MOVE_MED_ATTACK, Intent.ATTACK, this.damage.get(1).base, 3, true);
        } else if (moveByte == MOVE_STRONG_ATTACK) {
            this.setMove(MOVE_STRONG_ATTACK, Intent.ATTACK, this.damage.get(2).base);
        }
    }

    @Override
    protected void executeTurnFlow(byte moveByte) {
        if (moveByte == MOVE_WEAK_ATTACK) {
            // [약타 흐름] 1. 타격 액션 -> 2. 손상(Frail) 1 부여 액션
            AbstractDungeon.actionManager.addToBottom(new DamageAction(
                    AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.BLUNT_LIGHT
            ));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
                    AbstractDungeon.player, this, new FrailPower(AbstractDungeon.player, 1, true)
            ));
        } else if (moveByte == MOVE_MED_ATTACK) {
            // 🌟 [중타 흐름] for 문을 이용해 동일한 타격 액션을 3번 반복해서 큐에 밀어 넣습니다.
            for (int i = 0; i < 3; i++) {
                AbstractDungeon.actionManager.addToBottom(new DamageAction(
                        AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.SLASH_DIAGONAL
                ));
            }
        } else if (moveByte == MOVE_STRONG_ATTACK) {
            // [강타 흐름] 묵직한 한 방
            AbstractDungeon.actionManager.addToBottom(new DamageAction(
                    AbstractDungeon.player, this.damage.get(2), AbstractGameAction.AttackEffect.BLUNT_HEAVY
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
