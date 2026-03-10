package reactkr.patches;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatches;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.TextAboveCreatureEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.powers.mayo.MM_03_EvasionPower;
import reactkr.powers.mayo.MM_04_CounterPower;
import reactkr.relics.mayo.MM_04_tempRelic;

@SpirePatches({
        @SpirePatch(clz = AbstractPlayer.class, method = "damage", paramtypez = {DamageInfo.class}),
        @SpirePatch(clz = AbstractMonster.class, method = "damage", paramtypez = {DamageInfo.class})
})
public class MM_03_PlayerEvasionPatch {
    private static final Logger logger = LogManager.getLogger(MM_03_PlayerEvasionPatch.class.getName());

    // 2. 방어도가 깎이기 전, 메서드의 가장 첫 줄(rloc=0)에
    @SpireInsertPatch(rloc = 0)
    public static SpireReturn<Void> Insert(AbstractCreature __instance, DamageInfo info) {

        // 3. 적이 때린 일반 공격이고, 플레이어에게 '회피 파워'가 있다면
        if (info.type == DamageInfo.DamageType.NORMAL && info.owner != __instance && __instance.hasPower(MM_03_EvasionPower.POWER_ID)) {

            MM_03_EvasionPower pow = (MM_03_EvasionPower)__instance.getPower(MM_03_EvasionPower.POWER_ID);
            // 4. 난수 판정
            int rand = AbstractDungeon.cardRandomRng.random(1, 100);
            logger.info(rand);
            if (rand <= pow.amount + pow.ETERNAL_AMOUNT) {

                pow.flash();
                int decrease = pow.amount/2;
                if (__instance instanceof AbstractPlayer) {
                    AbstractPlayer p = (AbstractPlayer) __instance;
                    if (p.hasRelic(MM_04_tempRelic.ID)) {
                        decrease = pow.amount/4;
                    }
                }
                pow.amount = Math.max(pow.amount - decrease, pow.ETERNAL_AMOUNT);

                AbstractDungeon.effectList.add(new TextAboveCreatureEffect(info.owner.hb.cX, info.owner.hb.cY, "감나빗!", Color.LIME.cpy()));

                // *반격
                if (__instance.hasPower(MM_04_CounterPower.POWER_ID)) {
                    AbstractDungeon.actionManager.addToTop(new DamageAction(info.owner, new DamageInfo(__instance, __instance.getPower(MM_04_CounterPower.POWER_ID).amount)));
                }
                // 5. 즉시 종료
                return SpireReturn.Return(null);
            }
        }
        // 회피 확률에서 실패하거나 조건이 안 맞으면, 원래 엔진의 흐름대로 정상 진행
        return SpireReturn.Continue();
    }
}
