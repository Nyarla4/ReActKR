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
import java.util.Random;

import static reactkr.ModFile.makeID;

public class Event_Tenten extends AbstractImageEvent {
    public static final String ID = makeID("Tenten");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;

    private int screenNum = 0; // 현재 선택지 화면 번호

    private ArrayList<AbstractCard> rareCards = new ArrayList<>();

    public Event_Tenten() {
        super(NAME, DESCRIPTIONS[0], "reactkrResources/images/events/Tenten.png");

        // CardLibrary에 등록된 모든 카드를 순회합니다.
        for (Map.Entry<String, AbstractCard> entry : CardLibrary.cards.entrySet()) {
            AbstractCard card = entry.getValue();

            if (card.rarity == AbstractCard.CardRarity.RARE) {
                rareCards.add(card.makeCopy());
                // 카드의 복사본(makeCopy)을 넣는 것이 안전합니다.
            }
        }

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

                    Random r = new Random();
                    int idx = r.nextInt(rareCards.size());
                    AbstractCard targetCard = rareCards.get(idx);

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
