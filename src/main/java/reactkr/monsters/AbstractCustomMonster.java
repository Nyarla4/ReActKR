package reactkr.monsters;

import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public abstract class AbstractCustomMonster extends AbstractMonster {

    //현재 턴
    protected int turnCount = 0;

    public AbstractCustomMonster(String name, String id, int maxHealth, float hb_x, float hb_y, float hb_w, float hb_h, String imgUrl, float offsetX, float offsetY) {
        super(name, id, maxHealth, hb_x, hb_y, hb_w, hb_h, imgUrl, offsetX, offsetY);
    }

    public AbstractCustomMonster(String name, String id, int maxHealth, float hb_x, float hb_y, float hb_w, float hb_h, String imgUrl, float offsetX, float offsetY, boolean ignoreBlights) {
        super(name, id, maxHealth, hb_x, hb_y, hb_w, hb_h, imgUrl, offsetX, offsetY, ignoreBlights);
    }

    public AbstractCustomMonster(String name, String id, int maxHealth, float hb_x, float hb_y, float hb_w, float hb_h, String imgUrl) {
        super(name, id, maxHealth, hb_x, hb_y, hb_w, hb_h, imgUrl);
    }

    // =========================================================================
    // 💡 엔진 통제 구역 (자식 클래스에서 덮어쓰지 마세요)
    // =========================================================================

    /**
     * 턴 시작 시 호출
     * @param num AbstractDungeon.aiRng 가 제공하는 0~99 사이의 무작위 난수
     */
    @Override
    protected void getMove(int num) {
        // 1. 자식 클래스에게 현재 턴과 난수를 주고 이번 턴의 행동 식별자(byte)를 받아옵니다.
        byte moveByte = decideMoveByte(this.turnCount, num);

        // 2. 받아온 식별자를 바탕으로 몬스터의 의도(Intent)와 데미지를 구조적으로 세팅합니다.
        setIntentByByte(moveByte);
    }

    /**
     * 플레이어의 턴이 끝나고 몬스터가 행동할 때 호출
     */
    @Override
    public void takeTurn() {
        // 1. 자식 클래스에게 결정된 행동(nextMove)의 실제 액션 대기열 처리
        executeTurnFlow(this.nextMove);

        // 2. 턴 수 갱신
        this.turnCount++;

        // 3. 다음 행동에 사용할 난수 미리 처리
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    // =========================================================================
    // 💡 자식 클래스 강제 구현 구역 (추상 메서드)
    // =========================================================================

    /**
     * 1. 구조 매핑: n번째 턴, 혹은 무작위 난수에 따라 어떤 행동을 할지 식별자를 반환합니다.
     * @param turn 현재 몬스터의 턴 수 (0부터 시작)
     * @param rngNum 패턴 확률 분기를 위한 0~99 사이의 난수
     * @return 결정된 행동의 byte 식별자
     */
    protected abstract byte decideMoveByte(int turn, int rngNum);

    /**
     * 2. 구조 선언: decideMoveByte에서 결정된 식별자의 의도(Intent)를 설정합니다.
     * 반드시 내부에서 this.setMove()를 호출해야 합니다.
     * @param moveByte 결정된 행동 식별자
     */
    protected abstract void setIntentByByte(byte moveByte);

    /**
     * 3. 실행 흐름: 해당 식별자의 실제 액션(공격, 버프 등)을 ActionManager에 등록합니다.
     * @param moveByte 실행할 행동 식별자
     */
    protected abstract void executeTurnFlow(byte moveByte);
}
