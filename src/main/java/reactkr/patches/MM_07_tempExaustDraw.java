package reactkr.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import reactkr.powers.mayo.MM_07_tempExaustPower;

@SpirePatch(clz = GameActionManager.class, method = "update")
public class MM_07_tempExaustDraw {
    @SpirePostfixPatch
    public static void Postfix(GameActionManager __instance) {

        // 게임 진행 중인지
        if (AbstractDungeon.player != null && AbstractDungeon.getCurrRoom() != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {

            // 드로우 조건
            if (__instance.actions.isEmpty() && AbstractDungeon.player.hand.isEmpty() && !__instance.turnHasEnded && !AbstractDungeon.player.hasPower("No Draw") && !AbstractDungeon.isScreenUp && (AbstractDungeon.player.discardPile.size() > 0 || AbstractDungeon.player.drawPile.size() > 0)) {

                // 버프 확인
                AbstractPower myPower = AbstractDungeon.player.getPower(MM_07_tempExaustPower.POWER_ID);

                if (myPower != null) {
                    // 시각적 피드백
                    myPower.flash();

                    // 드로우 처리
                    AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, myPower.amount));
                }
            }
        }
    }
}
