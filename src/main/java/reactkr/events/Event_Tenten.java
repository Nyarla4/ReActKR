package reactkr.events;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import reactkr.Kuroka;
import reactkr.Latte;
import reactkr.Mayo;
import reactkr.relics.kuroka.MK_01_MajitomoRelic;
import reactkr.relics.latte.AL_01_TtediRelic;
import reactkr.relics.mayo.MM_01_NezmingRelic;

import static reactkr.ModFile.makeID;

public class Event_Tenten extends AbstractImageEvent {
    public static final String ID = makeID("Tenten");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;

    private int screenNum = 0; // 현재 선택지 화면 번호

    public Event_Tenten() {
        super(NAME, DESCRIPTIONS[0], "reactkrResources/images/events/Tenten.png");

        imageEventText.setDialogOption(OPTIONS[0]);
        imageEventText.setDialogOption(OPTIONS[1]);
        imageEventText.setDialogOption(OPTIONS[2]);
        imageEventText.setDialogOption(OPTIONS[3]);
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (screenNum) {
            case 0:
                if (buttonPressed == 2) { // 첫 번째 선택지 클릭 시

                    AbstractDungeon.player.heal(10);

                    this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                    this.imageEventText.updateDialogOption(0, OPTIONS[4]);
                    this.imageEventText.clearRemainingOptions();
                    screenNum = 1;
                } else {
                    this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                    this.imageEventText.updateDialogOption(0, OPTIONS[4]);
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
        return p.chosenClass == Kuroka.Enums.THE_KUROKA ||
                p.chosenClass == Mayo.Enums.THE_MAYO ||
                p.chosenClass == Latte.Enums.THE_LATTE;
    }
}
