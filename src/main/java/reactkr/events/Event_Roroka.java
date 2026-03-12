package reactkr.events;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import reactkr.Kuroka;
import reactkr.Latte;
import reactkr.Mayo;
import reactkr.cards.kuroka.MK_98_RoroGaki;

import static reactkr.ModFile.makeID;

public class Event_Roroka extends AbstractImageEvent {
    public static final String ID = makeID("Roroka");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;

    private int screenNum = 0; // 현재 선택지 화면 번호

    AbstractCard previewCard = new MK_98_RoroGaki();

    public Event_Roroka() {
        super(NAME, DESCRIPTIONS[0], "reactkrResources/images/events/Roroka.png");

        // 첫 번째 선택지: 로로 도발 획득
        imageEventText.setDialogOption(OPTIONS[0], previewCard);
        // 두 번째 선택지: 떠나기
        imageEventText.setDialogOption(OPTIONS[1]);
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (screenNum) {
            case 0:
                if (buttonPressed == 0) { // 첫 번째 선택지 클릭 시
                    //자코자코 오디오 todo
                    this.imageEventText.updateBodyText(DESCRIPTIONS[1]);

                    AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(previewCard, Settings.WIDTH/2.0f, Settings.HEIGHT/2.0f));

                    this.imageEventText.updateDialogOption(0, OPTIONS[2]);
                    this.imageEventText.clearRemainingOptions();
                    screenNum = 1;
                } else { // 떠나기 클릭 시
                    openMap();
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
