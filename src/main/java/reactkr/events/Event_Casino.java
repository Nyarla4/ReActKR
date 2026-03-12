package reactkr.events;

import com.megacrit.cardcrawl.cards.AbstractCard;
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

public class Event_Casino extends AbstractImageEvent {
    public static final String ID = makeID("Casino");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;

    private int screenNum = 0; // 현재 선택지 화면 번호

    private int stakes;
    private int pot;
    private int count;

    public Event_Casino() {
        super(NAME, DESCRIPTIONS[0], "reactkrResources/images/events/event_img.png");

        imageEventText.setDialogOption(OPTIONS[0]);
        imageEventText.setDialogOption(OPTIONS[1]);
        count = 0;
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
            case 1://도박 포기
                openMap();
                break;
            case 2://도박 시작
                if (buttonPressed == 0) {
                    stakes = 100;
                    AbstractDungeon.player.loseGold(stakes);
                    pot = stakes;
                    this.imageEventText.updateBodyText(DESCRIPTIONS[3] + this.pot + DESCRIPTIONS[4]);
                    this.imageEventText.clearRemainingOptions();
                    this.imageEventText.updateDialogOption(0, OPTIONS[5]);
                    this.imageEventText.setDialogOption(OPTIONS[6] + (pot ) + OPTIONS[7]);
                    screenNum = 3;
                } else if (buttonPressed == 1) {
                    stakes = 200;
                    AbstractDungeon.player.loseGold(stakes);
                    pot = stakes;
                    this.imageEventText.updateBodyText(DESCRIPTIONS[3] + this.pot + DESCRIPTIONS[4]);
                    this.imageEventText.clearRemainingOptions();
                    this.imageEventText.updateDialogOption(0, OPTIONS[5]);
                    this.imageEventText.setDialogOption(OPTIONS[6] + (pot) + OPTIONS[7]);
                    screenNum = 3;
                } else if (buttonPressed == 2) {
                    stakes = 300;
                    AbstractDungeon.player.loseGold(stakes);
                    pot = stakes;
                    this.imageEventText.updateBodyText(DESCRIPTIONS[3] + this.pot + DESCRIPTIONS[4]);
                    this.imageEventText.clearRemainingOptions();
                    this.imageEventText.updateDialogOption(0, OPTIONS[5]);
                    this.imageEventText.setDialogOption(OPTIONS[6] + (pot) + OPTIONS[7]);
                    screenNum = 3;
                } else if (buttonPressed == 3) {
                    this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                    this.imageEventText.updateDialogOption(0, OPTIONS[8]);
                    this.imageEventText.clearRemainingOptions();
                    screenNum = 1;
                }
                break;
            case 3://도박
                if (buttonPressed == 0) {
                    if (count < 10) {
                        Random random = new Random();
                        if (random.nextInt(2) > 0) {
                            count++;
                            this.pot += (this.pot / 2);
                            this.imageEventText.updateBodyText(DESCRIPTIONS[3] + this.pot + DESCRIPTIONS[4]);
                            this.imageEventText.updateDialogOption(0, OPTIONS[5]);
                            this.imageEventText.updateDialogOption(1, OPTIONS[6] + (pot) + OPTIONS[7]);
                        } else {
                            this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                            this.imageEventText.updateDialogOption(0, OPTIONS[8]);
                            this.imageEventText.clearRemainingOptions();
                            screenNum = 1;
                        }
                    } else {
                        AbstractDungeon.player.gainGold(pot);
                        this.imageEventText.updateBodyText(DESCRIPTIONS[5]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[8]);
                        this.imageEventText.clearRemainingOptions();
                        screenNum = 1;
                    }
                } else if (buttonPressed == 1) {
                    AbstractDungeon.player.gainGold(pot);
                    this.imageEventText.updateBodyText(DESCRIPTIONS[6]);
                    this.imageEventText.updateDialogOption(0, OPTIONS[8]);
                    this.imageEventText.clearRemainingOptions();
                    screenNum = 1;
                }
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
