package reactkr.events;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import reactkr.Kuroka;
import reactkr.Latte;
import reactkr.Mayo;

import java.util.Random;

import static reactkr.ModFile.makeID;

public class Event_Durian extends AbstractImageEvent {
    public static final String ID = makeID("Durian");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;

    private int screenNum = 0; // 현재 선택지 화면 번호

    public Event_Durian() {
        super(NAME, DESCRIPTIONS[0], "reactkrResources/images/events/event_img.png");

        imageEventText.setDialogOption(OPTIONS[0]);
        imageEventText.setDialogOption(OPTIONS[1]);
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (screenNum) {
            case 0://이벤트 시작 화면
                if (buttonPressed == 0) {
                    this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                    this.imageEventText.updateDialogOption(0, OPTIONS[2], AbstractDungeon.player.gold < 100);
                    this.imageEventText.updateDialogOption(1, OPTIONS[3], AbstractDungeon.player.gold < 200);
                    this.imageEventText.setDialogOption(OPTIONS[4], AbstractDungeon.player.gold < 300);
                    this.imageEventText.setDialogOption(OPTIONS[1]);
                    screenNum = 2;
                } else {
                    //this.imageEventText.loadImage("reactkrResources/images/events/tenten2.png");
                    this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                    this.imageEventText.updateDialogOption(0, OPTIONS[8]);
                    this.imageEventText.clearRemainingOptions();
                    screenNum = 1;
                }
                break;
            case 1:
                openMap();
                break;
        }
    }

    public static boolean canSpawn() {
        AbstractPlayer p = AbstractDungeon.player;
        return p.chosenClass.equals(Kuroka.Enums.THE_KUROKA) ||
                p.chosenClass.equals(Mayo.Enums.THE_MAYO) ||
                p.chosenClass.equals(Latte.Enums.THE_LATTE);
    }
}
