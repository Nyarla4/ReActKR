package reactkr.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import reactkr.Kuroka;
import reactkr.Latte;
import reactkr.Mayo;
import reactkr.cards.Kira_Song;
import reactkr.cards.UtaWaku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpirePatch(
        clz = AbstractDungeon.class,
        method = "getRewardCards" // 보상 카드를 생성하는 시점
)
public class CharacterSpecificCard {
    // 허용된 캐릭터 클래스 구조 정의
    private static final List<AbstractPlayer.PlayerClass> ALLOWED_CHARS = Arrays.asList(
            Kuroka.Enums.THE_KUROKA,
            Mayo.Enums.THE_MAYO,
            Latte.Enums.THE_LATTE
    );

    @SpirePostfixPatch
    public static ArrayList<AbstractCard> Postfix(ArrayList<AbstractCard> __result) {
        // [흐름] 현재 플레이어가 허용된 캐릭터가 아니라면, 보상 결과에서 특정 카드 제거
        if (!ALLOWED_CHARS.contains(AbstractDungeon.player.chosenClass)) {
            __result.removeIf(card -> card instanceof Kira_Song ||
                    card instanceof UtaWaku);
        }
        return __result;
    }
}