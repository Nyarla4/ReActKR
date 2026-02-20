package reactkr;

import basemod.abstracts.CustomEnergyOrb;
import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import reactkr.cards.mayo.MM_00_WarningShot;
import reactkr.cards.mayo.Mayo_Defend;
import reactkr.cards.mayo.Mayo_Strike;
import reactkr.relics.mayo.MM_00_MayoStartItemRelic;

import java.util.ArrayList;

import static reactkr.Mayo.Enums.MAYO_COLOR;
import static reactkr.ModFile.*;
import static reactkr.ModFile.MM_SHOULDER1;

public class Mayo extends CustomPlayer {

    static final String ID = makeID("Mayo");
    static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    static final String[] NAMES = characterStrings.NAMES;
    static final String[] TEXT = characterStrings.TEXT;


    public Mayo(String name, PlayerClass setClass) {
        super(name, setClass, new CustomEnergyOrb(orbTextures, makeCharacterPath("mayo/orb/vfx.png"), null), new SpriterAnimation(
                makeCharacterPath("mayo/static.scml")));
        initializeClass(null,
                MM_SHOULDER1,
                MM_SHOULDER2,
                MM_CORPSE,
                getLoadout(), 20.0F, -10.0F, 166.0F, 327.0F, new EnergyManager(3));


        dialogX = (drawX + 0.0F * Settings.scale);
        dialogY = (drawY + 240.0F * Settings.scale);
    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                80, 80, 1, 99, 5, this, getStartingRelics(),
                getStartingDeck(), false);
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            retVal.add(Mayo_Strike.ID);
        }
        for (int i = 0; i < 4; i++) {
            retVal.add(Mayo_Defend.ID);
        }
        retVal.add(MM_00_WarningShot.ID);
        return retVal;
    }

    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(MM_00_MayoStartItemRelic.ID);
        return retVal;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("UNLOCK_PING", MathUtils.random(-0.2F, 0.2F));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT,
                false);
    }

    private static final String[] orbTextures = {
            makeCharacterPath("mayo/orb/layer1.png"),
            makeCharacterPath("mayo/orb/layer2.png"),
            makeCharacterPath("mayo/orb/layer3.png"),
            makeCharacterPath("mayo/orb/layer4.png"),
            makeCharacterPath("mayo/orb/layer4.png"),
            makeCharacterPath("mayo/orb/layer6.png"),
            makeCharacterPath("mayo/orb/layer1d.png"),
            makeCharacterPath("mayo/orb/layer2d.png"),
            makeCharacterPath("mayo/orb/layer3d.png"),
            makeCharacterPath("mayo/orb/layer4d.png"),
            makeCharacterPath("mayo/orb/layer5d.png"),
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
        return MAYO_COLOR;
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
        return new MM_00_WarningShot();
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return NAMES[1];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new Mayo(name, chosenClass);
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
        public static PlayerClass THE_MAYO;
        @SpireEnum(name = "MAYO_COLOR")
        public static AbstractCard.CardColor MAYO_COLOR;
        @SpireEnum(name = "MAYO_COLOR")
        @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR;
    }
}
