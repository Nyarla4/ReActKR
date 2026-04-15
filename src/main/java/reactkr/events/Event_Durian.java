package reactkr.events;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import reactkr.Kuroka;
import reactkr.Latte;
import reactkr.Mayo;
import reactkr.monsters.DurianMonster;
import reactkr.relics.DurianMonsterRelic;

import static reactkr.ModFile.makeID;

public class Event_Durian extends AbstractImageEvent {
    public static final String ID = makeID("Durian");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;

    private int screenNum = 0; // 현재 선택지 화면 번호

    public Event_Durian() {
        super(NAME, DESCRIPTIONS[0], "reactkrResources/images/events/Durian_1.png");

        imageEventText.setDialogOption(OPTIONS[0]);
        imageEventText.setDialogOption(OPTIONS[1]);
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (screenNum) {
            case 0://이벤트 시작 화면
                if (buttonPressed == 0) {
                    this.screenNum = 2; // 전투 종료 후 화면 상태(case 2)로 번호 변경

                    AbstractDungeon.getCurrRoom().monsters = new MonsterGroup(new DurianMonster(0.0f, 0.0f));
                    AbstractDungeon.getCurrRoom().rewards.clear();
                    AbstractDungeon.getCurrRoom().addGoldToRewards(30);
                    AbstractDungeon.getCurrRoom().addRelicToRewards(new DurianMonsterRelic());

                    // 전투
                    this.enterCombatFromImage();
                } else {
                    this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                    this.imageEventText.updateDialogOption(0, OPTIONS[1]);
                    this.imageEventText.clearRemainingOptions();
                    screenNum = 1;
                }
                break;
            case 1:
            case 2://싸운다" 버튼을 눌렀을 때
                openMap();
                break;
        }
    }

    @Override
    public void reopen() {
        if (this.screenNum == 2) {
            // 필수: 전투 렌더링 상태를 이미지 이벤트 렌더링 상태로 강제 전환
            this.enterImageFromCombat();

            this.imageEventText.loadImage("reactkrResources/images/events/Durian_2.png");
            this.imageEventText.updateBodyText("두리안 괴인 잘 키워주세용. NL 감샤샤샤샤샤 합니당 NL 꾸벅");
            this.imageEventText.updateDialogOption(0, OPTIONS[2]);
            this.imageEventText.clearRemainingOptions();
        }
    }

    public static boolean canSpawn() {
        AbstractPlayer p = AbstractDungeon.player;
        return (p.chosenClass.equals(Kuroka.Enums.THE_KUROKA) ||
                p.chosenClass.equals(Mayo.Enums.THE_MAYO) ||
                p.chosenClass.equals(Latte.Enums.THE_LATTE))
                ;
    }
}
