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
import reactkr.monsters.WaruTomo;
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
        super(NAME, DESCRIPTIONS[0], "reactkrResources/images/events/event_img.png");

        imageEventText.setDialogOption(OPTIONS[0]);
        imageEventText.setDialogOption(OPTIONS[1]);
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (screenNum) {
            case 0://이벤트 시작 화면
                if (buttonPressed == 0) {
                    screenNum = 2;
                } else {
                    this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                    this.imageEventText.updateDialogOption(0, OPTIONS[1]);
                    this.imageEventText.clearRemainingOptions();
                    screenNum = 1;
                }
                break;
            case 1:
                openMap();
                break;
            case 2://싸운다" 버튼을 눌렀을 때

                // 전투 대상 세팅
                // 현재 방의 몬스터 그룹을 두리안 괴인으로 처리
                AbstractDungeon.getCurrRoom().monsters = new MonsterGroup(new DurianMonster(0.0f, 0.0f));

                // 보상 구조 세팅
                AbstractDungeon.getCurrRoom().rewards.clear(); // 혹시 모를 초기화

                // 일반적인 엘리트 전투 수준의 골드 추가 (약 25~35골드)
                AbstractDungeon.getCurrRoom().addGoldToRewards(30);

                // 두리안 괴인 유물 추가
                AbstractDungeon.getCurrRoom().addRelicToRewards(new DurianMonsterRelic());

                this.screenNum = 1;

                // 전투
                this.enterCombatFromImage();
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
