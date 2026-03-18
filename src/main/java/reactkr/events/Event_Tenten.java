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
import reactkr.cards.ThrowTheApple;
import reactkr.cards.TruckDriverTenten;
import reactkr.relics.SulkTentenRelic;

import java.util.ArrayList;
import java.util.Map;

import static reactkr.ModFile.makeID;

public class Event_Tenten extends AbstractImageEvent {
    public static final String ID = makeID("Tenten");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;

    private int screenNum = 0; // 현재 선택지 화면 번호

    private ArrayList<AbstractCard> tentenCards = new ArrayList<>();

    public Event_Tenten() {
        super(NAME, DESCRIPTIONS[0], "reactkrResources/images/events/Tenten.png");

        tentenCards.add(new TruckDriverTenten());
        tentenCards.add(new ThrowTheApple());

        imageEventText.setDialogOption(OPTIONS[0]);
        imageEventText.setDialogOption(OPTIONS[1]);
        imageEventText.setDialogOption(OPTIONS[2]);
        imageEventText.setDialogOption(OPTIONS[3]);
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (screenNum) {
            case 0:
                if (buttonPressed == 2) { // 정답 선택 시

                    int idx = AbstractDungeon.eventRng.random(0, tentenCards.size()-1);
                    AbstractCard targetCard = tentenCards.get(idx);

                    AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(targetCard, Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f));

                    this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                    this.imageEventText.updateDialogOption(0, OPTIONS[4]);
                    this.imageEventText.clearRemainingOptions();
                    screenNum = 1;
                } else {

                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain(
                            Settings.WIDTH / 2.0f,
                            Settings.HEIGHT / 2.0f,
                            new SulkTentenRelic()
                    );

                    this.imageEventText.loadImage("reactkrResources/images/events/tenten2.png");
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
        return p.chosenClass.equals(Kuroka.Enums.THE_KUROKA) ||
                p.chosenClass.equals(Mayo.Enums.THE_MAYO) ||
                p.chosenClass.equals(Latte.Enums.THE_LATTE);
    }
}
