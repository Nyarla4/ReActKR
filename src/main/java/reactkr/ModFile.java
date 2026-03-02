package reactkr;

import basemod.*;
import basemod.abstracts.DynamicVariable;
import basemod.eventUtil.AddEventParams;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import reactkr.cards.AbstractEasyCard;
import reactkr.cards.cardvars.AbstractEasyDynamicVariable;
import reactkr.configs.ModConfig;
import reactkr.events.*;
import reactkr.potions.AbstractEasyPotion;
import reactkr.potions.nanikaSpecial.NanikaFirePotion;
import reactkr.relics.AbstractEasyRelic;
import reactkr.util.ProAudio;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SuppressWarnings({"unused", "WeakerAccess"})
@SpireInitializer
public class ModFile implements
        PostInitializeSubscriber,
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        AddAudioSubscriber {

    public static final String modID = "reactkr";

    public static String makeID(String idText) {
        return modID + ":" + idText;
    }

    public static Color kurokaColor = Color.valueOf("7E7291");
    public static Color mayoColor = Color.valueOf("168F43");
    public static Color latteColor = Color.valueOf("7F97AC");
    public static Color characterColor = new Color(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1); // This should be changed eventually

    public static final String MK_SHOULDER1 = makeCharacterPath("kuroka/shoulder.png");
    public static final String MK_SHOULDER2 = makeCharacterPath("kuroka/shoulder2.png");
    public static final String MK_CORPSE = makeCharacterPath("kuroka/corpse.png");
    private static final String MK_ATTACK_S_ART = makeImagePath("512/mk_attack.png");
    private static final String MK_SKILL_S_ART = makeImagePath("512/mk_skill.png");
    private static final String MK_POWER_S_ART = makeImagePath("512/mk_power.png");
    private static final String MK_CARD_ENERGY_S = makeImagePath("512/mk_1_energy.png");
    private static final String MK_TEXT_ENERGY = makeImagePath("512/mk_1_text_energy.png");
    private static final String MK_ATTACK_L_ART = makeImagePath("1024/mk_attack.png");
    private static final String MK_SKILL_L_ART = makeImagePath("1024/mk_skill.png");
    private static final String MK_POWER_L_ART = makeImagePath("1024/mk_power.png");
    private static final String MK_CARD_ENERGY_L = makeImagePath("1024/mk_1_energy.png");
    private static final String MK_CHARSELECT_BUTTON = makeImagePath("charSelect/mk_charButton.png");
    private static final String MK_CHARSELECT_PORTRAIT = makeImagePath("charSelect/temp_mk_charBG.png");

    public static final String MM_SHOULDER1 = makeCharacterPath("mayo/shoulder.png");
    public static final String MM_SHOULDER2 = makeCharacterPath("mayo/shoulder2.png");
    public static final String MM_CORPSE = makeCharacterPath("mayo/corpse.png");
    private static final String MM_ATTACK_S_ART = makeImagePath("512/mm_attack.png");
    private static final String MM_SKILL_S_ART = makeImagePath("512/mm_skill.png");
    private static final String MM_POWER_S_ART = makeImagePath("512/mm_power.png");
    private static final String MM_CARD_ENERGY_S = makeImagePath("512/mm_1_energy.png");
    private static final String MM_TEXT_ENERGY = makeImagePath("512/mm_1_text_energy.png");
    private static final String MM_ATTACK_L_ART = makeImagePath("1024/mm_attack.png");
    private static final String MM_SKILL_L_ART = makeImagePath("1024/mm_skill.png");
    private static final String MM_POWER_L_ART = makeImagePath("1024/mm_power.png");
    private static final String MM_CARD_ENERGY_L = makeImagePath("1024/mm_1_energy.png");
    private static final String MM_CHARSELECT_BUTTON = makeImagePath("charSelect/charButton.png");
    private static final String MM_CHARSELECT_PORTRAIT = makeImagePath("charSelect/temp_mm_charBG.png");

    public static final String AL_SHOULDER1 = makeCharacterPath("latte/shoulder.png");
    public static final String AL_SHOULDER2 = makeCharacterPath("latte/shoulder2.png");
    public static final String AL_CORPSE = makeCharacterPath("latte/corpse.png");
    private static final String AL_ATTACK_S_ART = makeImagePath("512/al_attack.png");
    private static final String AL_SKILL_S_ART = makeImagePath("512/al_skill.png");
    private static final String AL_POWER_S_ART = makeImagePath("512/al_power.png");
    private static final String AL_CARD_ENERGY_S = makeImagePath("512/energy.png");
    private static final String AL_TEXT_ENERGY = makeImagePath("512/text_energy.png");
    private static final String AL_ATTACK_L_ART = makeImagePath("1024/al_attack.png");
    private static final String AL_SKILL_L_ART = makeImagePath("1024/al_skill.png");
    private static final String AL_POWER_L_ART = makeImagePath("1024/al_power.png");
    private static final String AL_CARD_ENERGY_L = makeImagePath("1024/energy.png");
    private static final String AL_CHARSELECT_BUTTON = makeImagePath("charSelect/charButton.png");
    private static final String AL_CHARSELECT_PORTRAIT = makeImagePath("charSelect/temp_al_charBG.png");

    public static Settings.GameLanguage[] SupportedLanguages = {
            Settings.GameLanguage.ENG,
            Settings.GameLanguage.KOR,
    };

    private String getLangString() {
        for (Settings.GameLanguage lang : SupportedLanguages) {
            if (lang.equals(Settings.language)) {
                return Settings.language.name().toLowerCase();
            }
        }
        return "eng";
    }

    public ModFile() {
        BaseMod.subscribe(this);

        BaseMod.addColor(Kuroka.Enums.KUROKA_COLOR, kurokaColor, kurokaColor, kurokaColor,
                kurokaColor, kurokaColor, kurokaColor, kurokaColor,
                MK_ATTACK_S_ART, MK_SKILL_S_ART, MK_POWER_S_ART, MK_CARD_ENERGY_S,
                MK_ATTACK_L_ART, MK_SKILL_L_ART, MK_POWER_L_ART,
                MK_CARD_ENERGY_L, MK_TEXT_ENERGY);

        BaseMod.addColor(Mayo.Enums.MAYO_COLOR, mayoColor, mayoColor, mayoColor,
                mayoColor, mayoColor, mayoColor, mayoColor,
                MM_ATTACK_S_ART, MM_SKILL_S_ART, MM_POWER_S_ART, MM_CARD_ENERGY_S,
                MM_ATTACK_L_ART, MM_SKILL_L_ART, MM_POWER_L_ART,
                MM_CARD_ENERGY_L, MM_TEXT_ENERGY);

        BaseMod.addColor(Latte.Enums.LATTE_COLOR, latteColor, latteColor, latteColor,
                latteColor, latteColor, latteColor, latteColor,
                AL_ATTACK_S_ART, AL_SKILL_S_ART, AL_POWER_S_ART, AL_CARD_ENERGY_S,
                AL_ATTACK_L_ART, AL_SKILL_L_ART, AL_POWER_L_ART,
                AL_CARD_ENERGY_L, AL_TEXT_ENERGY);
    }

    public static String makePath(String resourcePath) {
        return modID + "Resources/" + resourcePath;
    }

    public static String makeImagePath(String resourcePath) {
        return modID + "Resources/images/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return modID + "Resources/images/relics/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return modID + "Resources/images/powers/" + resourcePath;
    }

    public static String makeCharacterPath(String resourcePath) {
        return modID + "Resources/images/char/" + resourcePath;
    }

    public static String makeCardPath(String resourcePath) {
        return modID + "Resources/images/cards/" + resourcePath;
    }

    public static void initialize() {
        ModFile thismod = new ModFile();
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new Kuroka(Kuroka.characterStrings.NAMES[1], Kuroka.Enums.THE_KUROKA),
                MK_CHARSELECT_BUTTON, MK_CHARSELECT_PORTRAIT, Kuroka.Enums.THE_KUROKA);
        if (ModConfig.showMayo) {
            BaseMod.addCharacter(new Mayo(Mayo.characterStrings.NAMES[1], Mayo.Enums.THE_MAYO),
                    MM_CHARSELECT_BUTTON, MM_CHARSELECT_PORTRAIT, Mayo.Enums.THE_MAYO);
        }
        if (ModConfig.showLatte) {
            BaseMod.addCharacter(new Latte(Latte.characterStrings.NAMES[1], Latte.Enums.THE_LATTE),
                    AL_CHARSELECT_BUTTON, AL_CHARSELECT_PORTRAIT, Latte.Enums.THE_LATTE);
        }

//        new AutoAdd(modID)
//            .packageFilter(AbstractEasyPotion.class)
//            .any(AbstractEasyPotion.class, (info, potion) -> {
//                if (potion.pool == null)
//                    BaseMod.addPotion(potion.getClass(), potion.liquidColor, potion.hybridColor, potion.spotsColor, potion.ID);
//                else
//                    BaseMod.addPotion(potion.getClass(), potion.liquidColor, potion.hybridColor, potion.spotsColor, potion.ID, potion.pool);
//            });
        new AutoAdd(modID)
                .packageFilter("reactkr.potions") // 포션이 들어있는 패키지 경로
                .any(AbstractPotion.class, (info, potion) -> {
                    // 특정 조건(예: pool이 특정값)일 때만 등록하거나, 캐릭터 인자를 null로 주어 일반 풀에서 제외합니다.
                    if (potion instanceof NanikaFirePotion) {
                        // 이 포션은 시스템엔 존재하지만, 어떤 캐릭터의 무작위 보상 풀에도 들어가지 않음
                        BaseMod.addPotion(potion.getClass(), potion.liquidColor, potion.hybridColor, potion.spotsColor, potion.ID, null);
                    } else if (potion instanceof AbstractEasyPotion) {
                        // 기존 등록 흐름 유지
                        BaseMod.addPotion(potion.getClass(), potion.liquidColor, potion.hybridColor, potion.spotsColor, potion.ID, ((AbstractEasyPotion) potion).pool);
                    } else {
                        BaseMod.addPotion(potion.getClass(), potion.liquidColor, potion.hybridColor, potion.spotsColor, potion.ID, null);
                    }
                });
    }

    @Override
    public void receiveEditRelics() {
        new AutoAdd(modID)
                .packageFilter(AbstractEasyRelic.class)
                .any(AbstractEasyRelic.class, (info, relic) -> {
                    if (relic.color == null) {
                        BaseMod.addRelic(relic, RelicType.SHARED);
                    } else if (relic.color == Mayo.Enums.MAYO_COLOR) {
                        if (ModConfig.showMayo) {
                            BaseMod.addRelicToCustomPool(relic, relic.color);
                        }
                    } else if (relic.color == Latte.Enums.LATTE_COLOR) {
                        if (ModConfig.showLatte) {
                            BaseMod.addRelicToCustomPool(relic, relic.color);
                        }
                    } else {
                        BaseMod.addRelicToCustomPool(relic, relic.color);
                    }
                    if (!info.seen) {
                        UnlockTracker.markRelicAsSeen(relic.relicId);
                    }
                });
    }

    @Override
    public void receiveEditCards() {
        new AutoAdd(modID)
                .packageFilter(AbstractEasyDynamicVariable.class)
                .any(DynamicVariable.class, (info, var) ->
                        BaseMod.addDynamicVariable(var));
        new AutoAdd(modID)
                .packageFilter(AbstractEasyCard.class)
                .setDefaultSeen(true)
                .cards();
//        new AutoAdd(modID)
//                .packageFilter(AbstractEasyCard_Kuroka.class)
//                .setDefaultSeen(true)
//                .cards();
//        new AutoAdd(modID)
//                .packageFilter(AbstractEasyCard_Mayo.class)
//                .setDefaultSeen(true)
//                .cards();
//        new AutoAdd(modID)
//                .packageFilter(AbstractEasyCard_Latte.class)
//                .setDefaultSeen(true)
//                .cards();
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, modID + "Resources/localization/" + getLangString() + "/Cardstrings.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, modID + "Resources/localization/" + getLangString() + "/Relicstrings.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, modID + "Resources/localization/" + getLangString() + "/Charstrings.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, modID + "Resources/localization/" + getLangString() + "/Powerstrings.json");
        BaseMod.loadCustomStringsFile(UIStrings.class, modID + "Resources/localization/" + getLangString() + "/UIstrings.json");
        BaseMod.loadCustomStringsFile(OrbStrings.class, modID + "Resources/localization/" + getLangString() + "/Orbstrings.json");
        BaseMod.loadCustomStringsFile(StanceStrings.class, modID + "Resources/localization/" + getLangString() + "/Stancestrings.json");
        BaseMod.loadCustomStringsFile(PotionStrings.class, modID + "Resources/localization/" + getLangString() + "/Potionstrings.json");
        BaseMod.loadCustomStringsFile(EventStrings.class, modID + "Resources/localization/" + getLangString() + "/Eventstrings.json");

        ModConfig.initialize();
    }

    @Override
    public void receiveAddAudio() {
        for (ProAudio a : ProAudio.values()) {
            BaseMod.addAudio(makeID(a.name()), makePath("audio/" + a.name().toLowerCase() + ".mp3"));
        }
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(modID + "Resources/localization/" + getLangString() + "/Keywordstrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(modID, keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    @Override
    public void receivePostInitialize() {
        AddEvent();
        AddModConfig();
        if(ModConfig.showLatte){
            ReplaceDate();
        }
    }

    private void AddEvent() {
        // 이벤트 ID, 클래스명, 등장할 던전 ID (Exordium, City, Beyond)
        BaseMod.addEvent(new AddEventParams.Builder(Event_Roroka.ID, Event_Roroka.class)
                .spawnCondition(Event_Roroka::canSpawn)
                .create());
        BaseMod.addEvent(new AddEventParams.Builder(Event_Mination.ID, Event_Mination.class)
                .spawnCondition(Event_Mination::canSpawn)
                .create());
        BaseMod.addEvent(new AddEventParams.Builder(Event_DontBelieveInR.ID, Event_DontBelieveInR.class)
                .spawnCondition(Event_DontBelieveInR::canSpawn)
                .create());
        BaseMod.addEvent(new AddEventParams.Builder(Event_Fan.ID, Event_Fan.class)
                .spawnCondition(Event_Fan::canSpawn)
                .create());
        BaseMod.addEvent(new AddEventParams.Builder(Event_Tenten.ID, Event_Tenten.class)
                .spawnCondition(Event_Tenten::canSpawn)
                .create());
    }
    private void AddModConfig() {
        Texture badgeTexture = ImageMaster.loadImage("reactkrResources/images/powers/ReactKRRangersPower32.png");
        ModPanel settingsPanel = new ModPanel();
        // 1번 버튼 흐름: 마요 캐릭터 제어
        ModLabeledToggleButton mayoBtn = new ModLabeledToggleButton(
                "마요 보기(재시작 필요)", 700f, 700f, mayoColor, FontHelper.charDescFont,
                ModConfig.showMayo, settingsPanel, (label) -> {
        },
                (button) -> {
                    ModConfig.showMayo = button.enabled; // 마요 구조 데이터 갱신
                    ModConfig.save();
                }
        );
        // 2번 버튼 흐름: 라떼 캐릭터 제어 (Y축 좌표를 다르게 하여 아래에 배치)
        ModLabeledToggleButton latteBtn = new ModLabeledToggleButton(
                "라떼 보기(재시작 필요)", 700f, 650f, latteColor, FontHelper.charDescFont,
                ModConfig.showLatte, settingsPanel, (label) -> {
        },
                (button) -> {
                    ModConfig.showLatte = button.enabled; // 라떼 구조 데이터 갱신
                    ModConfig.save();
                }
        );
        settingsPanel.addUIElement(latteBtn);
        settingsPanel.addUIElement(mayoBtn);
        BaseMod.registerModBadge(badgeTexture, "ReActKR", "SinsaTomo", "Desc", settingsPanel);
    }
    private void ReplaceDate(){
        CharacterStrings charStrings = CardCrawlGame.languagePack.getCharacterString(Latte.ID);
        if (charStrings != null && charStrings.TEXT != null && charStrings.TEXT.length > 0) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("Mdd");
            String todayDate = LocalDate.now().format(formatter);
            charStrings.TEXT[0] = charStrings.TEXT[0].replace("{DATE}", todayDate);
        }
    }
}
