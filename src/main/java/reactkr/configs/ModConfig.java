package reactkr.configs;

import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;

import java.util.Properties;

public class ModConfig {
    public static SpireConfig config;
    public static boolean showMayo = false;
    public static boolean showLatte = false;

    public static void initialize() {
        try {
            Properties defaults = new Properties();
            defaults.setProperty("showMayo", "false");
            defaults.setProperty("showLatte", "false"); // [구조] 기본값 정의

            // 파일 구조 생성
            config = new SpireConfig("ReactKRMod", "config", defaults);

            // [핵심 흐름] 파일로부터 데이터를 읽어와서 실제 메모리(변수)에 할당
            showMayo = config.getBool("showMayo");
            showLatte = config.getBool("showLatte");

            System.out.println("Config Loaded: Latte=" + showLatte + ", Mayo=" + showMayo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        if (config != null) {
            try {
                // [구조적 동기화] 메모리의 변수값을 파일 구조에 기록
                config.setBool("showMayo", showMayo);
                config.setBool("showLatte", showLatte);

                // [흐름] 물리적인 파일로 내보내기(실제 저장 수행)
                config.save();
                System.out.println("Config Saved!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}