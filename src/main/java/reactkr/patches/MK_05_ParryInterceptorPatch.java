package reactkr.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.actions.MK_05_ParryWaitingAction;
import reactkr.cards.kuroka.MK_05_ParryMan;

import static reactkr.ModFile.makeID;

@SpirePatch(clz = GameActionManager.class, method = "getNextAction")
public class MK_05_ParryInterceptorPatch {
    // 이번 턴에 패링이 이미 실행되었는지 확인하는 내부 플래그
    public static boolean alreadyTriggeredThisTurn = false;

    @SpirePrefixPatch
    public static SpireReturn<Void> Prefix(GameActionManager __instance) {
        // 1. 플레이어 턴이 끝났고, 전투 중일 때만 작동
        if (AbstractDungeon.actionManager.turnHasEnded && !alreadyTriggeredThisTurn) {

            // 2. 현재 공격 의도를 가진 몬스터가 있는지 확인
            for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!m.isDeadOrEscaped() && m.intent != null && m.intent.name().contains("ATTACK")) {

                    // 3. 손에 패링맨 카드가 있는지 확인
                    boolean hasCard = AbstractDungeon.player.hand.group.stream()
                            .anyMatch(c -> c.cardID.equals(makeID("ParryMan")));

                    if (hasCard) {

                        float duration = 1.5f;
                        for(AbstractCard c:AbstractDungeon.player.hand.group) {
                            if(c instanceof MK_05_ParryMan) {
                                duration = ((MK_05_ParryMan)c).parryDuration;
                                break;
                            }
                        }
                        alreadyTriggeredThisTurn = true; // 중복 발동 방지
                        AbstractDungeon.actionManager.addToTop(new MK_05_ParryWaitingAction(m, duration));
                        return SpireReturn.Return(); // 패링 액션을 넣었으니 이번 프레임은 중단
                    }
                }
            }
        }
        return SpireReturn.Continue();
    }

    // 턴 시작 시 플래그 초기화
    @SpirePatch(clz = GameActionManager.class, method = "getNextAction")
    public static class ResetPatch {
        @SpirePrefixPatch
        public static void Prefix(GameActionManager __instance) {
            if (!AbstractDungeon.actionManager.turnHasEnded) {
                alreadyTriggeredThisTurn = false;
            }
        }
    }
}