package reactkr;

import basemod.abstracts.CustomEnergyOrb;
import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.cards.kuroka.*;
import reactkr.powers.kuroka.MK_04_Witchification_Power;
import reactkr.relics.kuroka.MK_00_KurokaStartItemRelic;
import reactkr.util.ProAudio;

import java.util.*;

import static reactkr.Kuroka.Enums.KUROKA_COLOR;
import static reactkr.ModFile.*;
import static reactkr.util.Wiz.playAudio;

public class Kuroka extends CustomPlayer {

    static final String ID = makeID("Kuroka");
    static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    static final String[] NAMES = characterStrings.NAMES;
    static final String[] TEXT = characterStrings.TEXT;

    private Texture originTexture;
    private Texture witchTexture;

    public int RorokaMaxHp = 24;

    public int baseBattlePotionSlots;

    private static final Logger logger = LogManager.getLogger(Kuroka.class.getName());

    public Kuroka(String name, PlayerClass setClass) {
        super(name, setClass, new CustomEnergyOrb(orbTextures, makeCharacterPath("kuroka/orb/vfx.png"), null),
                new SpriterAnimation(makeCharacterPath("kuroka/static.scml")));
        initializeClass(null,
                MK_SHOULDER1,
                MK_SHOULDER2,
                MK_CORPSE,
                getLoadout(), 20.0F, -10.0F, 166.0F, 327.0F, new EnergyManager(3));


        dialogX = (drawX + 0.0F * Settings.scale);
        dialogY = (drawY + 240.0F * Settings.scale);

        originTexture = ImageMaster.loadImage("reactkrResources/images/char/kuroka/main.png");
        witchTexture = ImageMaster.loadImage("reactkrResources/images/char/kuroka/main_witch.png");

        this.img = originTexture;
    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                75, 75, 1, 99, 5, this, getStartingRelics(),
                getStartingDeck(), false);
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(MK_00_1_RoroHi.ID);
        retVal.add(MK_00_2_TwoFairy.ID);
        for (int i = 0; i < 4; i++) {
            retVal.add(Kuroka_Strike.ID);
        }
        for (int i = 0; i < 4; i++) {
            retVal.add(Kuroka_Defend.ID);
        }
//        retVal.add(MK_15_JingaiJoa.ID);
//        retVal.add(MK_15_JingaiJoa.ID);
//        retVal.add(MK_15_JingaiJoa.ID);
//        retVal.add(MK_13_YuriJoa.ID);
//        retVal.add(MK_13_YuriJoa.ID);
//        retVal.add(MK_13_YuriJoa.ID);
        return retVal;
    }

    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(MK_00_KurokaStartItemRelic.ID);
        return retVal;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        playAudio(ProAudio.MAJIHI);
        Random rng = new Random();
        if (rng.nextFloat() < 0.25f) { // 25% 확률
            //logger.info("25%");
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    playAudio(ProAudio.ROROHI);
                }
            }, 850); // 850ms 후에 실행
        }
        //CardCrawlGame.sound.playA("UNLOCK_PING", MathUtils.random(-0.2F, 0.2F));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT,
                false);
    }

    private static final String[] orbTextures = {
            makeCharacterPath("kuroka/orb/layer1.png"),
            makeCharacterPath("kuroka/orb/layer2.png"),
            makeCharacterPath("kuroka/orb/layer3.png"),
            makeCharacterPath("kuroka/orb/layer4.png"),
            makeCharacterPath("kuroka/orb/layer4.png"),
            makeCharacterPath("kuroka/orb/layer6.png"),
            makeCharacterPath("kuroka/orb/layer1d.png"),
            makeCharacterPath("kuroka/orb/layer2d.png"),
            makeCharacterPath("kuroka/orb/layer3d.png"),
            makeCharacterPath("kuroka/orb/layer4d.png"),
            makeCharacterPath("kuroka/orb/layer5d.png"),
    };

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "UNLOCK_PING";
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 8;
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return KUROKA_COLOR;
    }

    @Override
    public Color getCardTrailColor() {
        return characterColor.cpy();
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new MK_00_2_TwoFairy();
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return NAMES[1];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new Kuroka(name, chosenClass);
    }

    @Override
    public Color getCardRenderColor() {
        return characterColor.cpy();
    }

    @Override
    public Color getSlashAttackColor() {
        return characterColor.cpy();
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.FIRE,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.FIRE};
    }

    @Override
    public String getSpireHeartText() {
        return TEXT[1];
    }

    @Override
    public String getVampireText() {
        return TEXT[2];
    }

    public static class Enums {
        @SpireEnum
        public static PlayerClass THE_KUROKA;
        @SpireEnum(name = "KUROKA_COLOR")
        public static AbstractCard.CardColor KUROKA_COLOR;
        @SpireEnum(name = "KUROKA_COLOR")
        @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR;
    }

    @Override
    public void renderPlayerImage(SpriteBatch sb) {
        if (this.hasPower(MK_04_Witchification_Power.POWER_ID)) {
            sb.setColor(Color.WHITE);

            if (this.witchTexture != null) {
                float width = (float)this.witchTexture.getWidth() * Settings.scale;   // 약 166.6
                float height = (float)this.witchTexture.getHeight() * Settings.scale; // 약 200.0 (예상)
                logger.info("scale: " + Settings.scale);
                // 1. [X축] 많이 왼쪽이므로, -width(166) 대신 절반 정도인 -80~90 픽셀만 밀어봅니다.
                // 2. [Y축] 살짝 위이므로, 기준점에서 10~20 픽셀 정도 아래로 내립니다.
                sb.draw(
                        this.witchTexture,
                        this.drawX + this.animX - (width * 0.4f),
                        this.drawY + this.animY + AbstractDungeon.sceneOffsetY, // 살짝 아래로 보정
                        width,
                        height,
                        0, 0,
                        this.witchTexture.getWidth(),
                        this.witchTexture.getHeight(),
                        this.flipHorizontal,
                        this.flipVertical
                );
            }
        } else {
            super.renderPlayerImage(sb);
        }
    }

    public List<CutscenePanel> getCutscenePanels() {
        List<CutscenePanel> panels = new ArrayList();
        panels.add(new CutscenePanel("reactkrResources/images/BG/MajinaiKuroka_BG0.png"));
        panels.add(new CutscenePanel("reactkrResources/images/BG/MajinaiKuroka_BG1.png", "majihi2"));
        panels.add(new CutscenePanel("reactkrResources/images/BG/MajinaiKuroka_BG2.png", "majihi2"));
        panels.add(new CutscenePanel("reactkrResources/images/BG/MajinaiKuroka_BG3.png", "majihi2"));
        panels.add(new CutscenePanel("reactkrResources/images/BG/End_credits.png"));
        panels.add(new CutscenePanel("reactkrResources/images/BG/End_credits.png"));
        return panels;
    }
    //baseBattlePotionSlots
}
