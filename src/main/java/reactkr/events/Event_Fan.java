package reactkr.events;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import reactkr.Kuroka;
import reactkr.Latte;
import reactkr.Mayo;
import reactkr.cards.kuroka.MK_98_RoroGaki;
import reactkr.relics.kuroka.MK_01_MajitomoRelic;
import reactkr.relics.latte.AL_01_TtediRelic;
import reactkr.relics.mayo.MM_01_NezmingRelic;

import static reactkr.ModFile.makeID;

public class Event_Fan extends AbstractImageEvent {
    public static final String ID = makeID("Fan");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;

    private int screenNum = 0; // 현재 선택지 화면 번호

    AbstractRelic previewRelic;

    public Event_Fan() {
        super(NAME, DESCRIPTIONS[0], "reactkrResources/images/events/Fan.png");

        AbstractPlayer p = AbstractDungeon.player;
        String option = "";
        if (p != null) {
            previewRelic = p.chosenClass.equals(Kuroka.Enums.THE_KUROKA) ? new MK_01_MajitomoRelic() :
                    p.chosenClass.equals(Mayo.Enums.THE_MAYO) ? new MM_01_NezmingRelic() :
                    p.chosenClass.equals(Latte.Enums.THE_LATTE) ? new AL_01_TtediRelic() :
                    null;
        }

        imageEventText.setDialogOption(OPTIONS[0], previewRelic);
        imageEventText.setDialogOption(OPTIONS[1]);
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (screenNum) {
            case 0:
                if (buttonPressed == 0) {
                    String selectedDesc;

                    AbstractPlayer p = AbstractDungeon.player;

                    selectedDesc = p.chosenClass == Kuroka.Enums.THE_KUROKA ? DESCRIPTIONS[1] :
                            p.chosenClass == Mayo.Enums.THE_MAYO ? DESCRIPTIONS[2] :
                                    p.chosenClass == Latte.Enums.THE_LATTE ? DESCRIPTIONS[3] : null;

                    this.imageEventText.updateBodyText(selectedDesc);

                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain(
                            Settings.WIDTH / 2.0f,
                            Settings.HEIGHT / 2.0f,
                            previewRelic
                    );

                    this.imageEventText.updateDialogOption(0, OPTIONS[2]);
                    this.imageEventText.clearRemainingOptions();
                    screenNum = 2;
                } else if (buttonPressed == 1) {
                    this.imageEventText.updateBodyText(DESCRIPTIONS[4]);

                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain(
                            Settings.WIDTH / 2.0f,
                            Settings.HEIGHT / 2.0f,
                            previewRelic
                    );

                    this.imageEventText.updateDialogOption(0, OPTIONS[2]);
                    this.imageEventText.clearRemainingOptions();
                    screenNum = 2;
                } else { // 떠나기 클릭 시
                    openMap();
                }
                break;
            case 2:
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
