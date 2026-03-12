package reactkr.events;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import reactkr.Kuroka;
import reactkr.Latte;
import reactkr.Mayo;
import reactkr.relics.SulkTentenRelic;

import java.util.ArrayList;
import java.util.Map;

import static reactkr.ModFile.makeID;

public class Event_Lucky extends AbstractImageEvent {
    public static final String ID = makeID("Lucky");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;

    private int screenNum = 0; // 현재 선택지 화면 번호

    public Event_Lucky() {
        super(NAME, DESCRIPTIONS[0], "reactkrResources/images/events/event_img.png");

        imageEventText.setDialogOption(OPTIONS[0]);
        imageEventText.setDialogOption(OPTIONS[1]);
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (screenNum) {
            case 0:
                if (buttonPressed == 0) {
                    int deckSize = AbstractDungeon.player.masterDeck.size();

                    for (int i = 0; i < deckSize; i++) {

                        // 기존 카드
                        AbstractCard oldCard = AbstractDungeon.player.masterDeck.group.get(i);

                        // 무작위 카드
                        AbstractCard newCard = AbstractDungeon.returnTrulyRandomCardFromAvailable(oldCard, AbstractDungeon.cardRandomRng).makeCopy();

                        // 강화 처리
                        if (newCard.canUpgrade()) {
                            newCard.upgrade();
                        }

                        // 교체
                        AbstractDungeon.player.masterDeck.group.set(i, newCard);
                    }

                    //다른 소리로 바꿔도 됨
                    CardCrawlGame.sound.play("GHOST_ORB_IGNITE_1");

                    this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                    this.imageEventText.updateDialogOption(0, OPTIONS[2]);
                    this.imageEventText.clearRemainingOptions();
                    screenNum = 1;
                } else {
                    //this.imageEventText.loadImage("reactkrResources/images/events/tenten2.png");
                    this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                    this.imageEventText.updateDialogOption(0, OPTIONS[2]);
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
        return p.chosenClass.equals(Mayo.Enums.THE_MAYO);
    }
}
