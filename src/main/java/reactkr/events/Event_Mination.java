package reactkr.events;


import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
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
import reactkr.cards.curse.CURSE_01_Kusa;
import reactkr.relics.DurianRelic;

import static reactkr.ModFile.makeID;

public class Event_Mination extends AbstractImageEvent {

    public static final String ID = makeID("Mination");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;

    private int screenNum = 0; // 현재 선택지 화면 번호

    AbstractRelic previewRelic = new DurianRelic();
    AbstractCard previewCurse = new CURSE_01_Kusa();

    public Event_Mination() {
        super(NAME, DESCRIPTIONS[0], "reactkrResources/images/events/Mination.png");

        // 첫 번째 선택지: 연어장 선택
        imageEventText.setDialogOption(OPTIONS[0]);
        // 두 번째 선택지: 두리안 선택
        imageEventText.setDialogOption(OPTIONS[1], previewCurse, previewRelic);
        // 세 번째 선택지: 미 선택
        imageEventText.setDialogOption(OPTIONS[2]);
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (screenNum) {
            case 0:
                if (buttonPressed == 0) { // 첫 번째 선택지 클릭 시
                    AbstractDungeon.player.damage(new DamageInfo(null, 20, DamageInfo.DamageType.HP_LOSS));
                    AbstractDungeon.player.gainGold(50);
                    this.imageEventText.updateBodyText(DESCRIPTIONS[1]);

                    this.imageEventText.updateDialogOption(0, OPTIONS[2]);
                    this.imageEventText.clearRemainingOptions();
                    screenNum = 1;
                } else if (buttonPressed == 1) { // 두 번째 선택지 클릭 시
                    this.imageEventText.updateBodyText(DESCRIPTIONS[2]);

                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain(
                            Settings.WIDTH / 2.0f,
                            Settings.HEIGHT / 2.0f,
                            previewRelic
                    );
                    AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(previewCurse, Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f));

                    this.imageEventText.updateDialogOption(0, OPTIONS[2]);
                    this.imageEventText.clearRemainingOptions();
                    screenNum = 1;
                } else { // 세 번째 클릭 시
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
        return p.chosenClass == Kuroka.Enums.THE_KUROKA ||
                p.chosenClass == Mayo.Enums.THE_MAYO ||
                p.chosenClass == Latte.Enums.THE_LATTE;
    }
}
