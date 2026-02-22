package reactkr.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import reactkr.configs.ModConfig;

import java.util.ArrayList;

import static reactkr.Latte.Enums.LATTE_COLOR;
import static reactkr.Mayo.Enums.MAYO_COLOR;
import static reactkr.ModFile.makeID;

@SpirePatch(
        clz = CardLibrary.class,
        method = "getCardList"
)
public class HideCardLibrary {
    @SpirePostfixPatch
    public static ArrayList<AbstractCard> Postfix(ArrayList<AbstractCard> __result, CardLibrary.LibraryType type) {
        __result.removeIf(c -> {
            boolean result;
            result = c.cardID.startsWith(makeID("TheThing")) ||
                    c.cardID.startsWith(makeID("Select")) ||
                    c.cardID.equals(makeID("Seven")) ||
                    c.cardID.equals(makeID("Five"))
            ;
            if (!ModConfig.showMayo) {
                result = result || c.color == MAYO_COLOR;
            }
            if (!ModConfig.showLatte) {
                result = result || c.color == LATTE_COLOR;
            }
            return result;
        });

        return __result;
    }
}
