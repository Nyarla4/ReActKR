package reactkr.util;

import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MK_05_ParryManager {
    public static boolean isParryWindow = false; // 현재 패링 조작이 가능한지
    public static AbstractMonster currentAttacker = null; // 공격 중인 적
    public static boolean parrySuccess = false; // 패링 성공 여부

    public static float renderDuration = 0f;

    public static void startWindow(AbstractMonster m) {
        isParryWindow = true;
        currentAttacker = m;
        parrySuccess = false;
    }

    public static void stopWindow() {
        isParryWindow = false;
        currentAttacker = null;
    }
}
