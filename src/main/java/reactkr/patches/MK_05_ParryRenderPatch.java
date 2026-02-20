package reactkr.patches;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import reactkr.util.MK_05_ParryManager;

@SpirePatch(clz = AbstractDungeon.class, method = "render")
public class MK_05_ParryRenderPatch {
    public static void Postfix(AbstractDungeon __instance, SpriteBatch sb) {
        if (MK_05_ParryManager.isParryWindow) {
            // 매니저에서 업데이트된 시간을 가져옴
            float timeLeft = MK_05_ParryManager.renderDuration;

            if (timeLeft > 0) {
                String msg = String.format("PARRY TIME: %.1fs", timeLeft);

                // 텍스트 렌더링
                FontHelper.renderFontCentered(sb,
                        FontHelper.bannerNameFont,
                        msg,
                        Settings.WIDTH / 2f,
                        Settings.HEIGHT / 2f + 250f * Settings.scale,
                        Color.YELLOW.cpy()
                );
            }
        }
    }
}