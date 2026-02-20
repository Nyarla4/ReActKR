package reactkr.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.helpers.PotionHelper;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.random.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

@SpirePatch(clz = PotionHelper.class, method = "getRandomPotion", paramtypez = {Random.class})
public class PotionFilter {
    private static ArrayList<String> backup = new ArrayList<>();
    private static final Logger logger = LogManager.getLogger(PotionFilter.class.getName());

    // [구조] 매개변수가 없는 메서드를 위한 패치 클래스
    @SpirePatch(clz = PotionHelper.class, method = "getRandomPotion", paramtypez = {})
    public static class NoParamPatch {
        @SpirePrefixPatch
        public static void Prefix() {
            PotionFilter.doPrefix();
        }
        @SpirePostfixPatch
        public static void Postfix() {
            PotionFilter.doPostfix();
        }
    }

    // [구조] Random 인자가 있는 메서드를 위한 패치 클래스
    @SpirePatch(clz = PotionHelper.class, method = "getRandomPotion", paramtypez = {Random.class})
    public static class WithParamPatch {
        @SpirePrefixPatch
        public static void Prefix(Random rng) {
            PotionFilter.doPrefix();
        }
        @SpirePostfixPatch
        public static void Postfix(Random rng) {
            PotionFilter.doPostfix();
        }
    }

    // [수행 과정] 공통 로직 분리 (중복 제거)
    public static void doPrefix() {
        // 1. [구조 백업] 나중에 복구하기 위해 원본 전체를 백업합니다.
        backup.clear();
        backup.addAll(PotionHelper.potions);

        // 2. [필터링 수행 흐름] 원본 리스트에서 조건에 맞는 것들을 제거합니다.
        // Iterator를 사용하거나 removeIf를 사용하는 것이 구조적으로 안전합니다.
        PotionHelper.potions.removeIf(pId -> {
            // "제거대상"을 포함하고 있으면서
            if (pId.contains("Nanika")) {
                // "예외대상"은 포함하지 않는 경우에만 제거!
                return !pId.contains("SaintWater");
            }
            return false; // 그 외(제거대상이 아니면)는 유지
        });
    }

    public static void doPostfix() {
        PotionHelper.potions.clear();
        PotionHelper.potions.addAll(backup);
    }
}
