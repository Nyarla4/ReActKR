package reactkr.events;


import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.colorless.Apotheosis;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import reactkr.Kuroka;
import reactkr.cards.kuroka.MK_99_Scholar;
import reactkr.cards.subcards.AL_00_1_SelectIceAmericano;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static reactkr.ModFile.makeID;

/***
 * 쿠로카 전용 이벤트
 */
public class Event_DontBelieveInR extends AbstractImageEvent {

    public static final String ID = makeID("DontBelieveInR");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;

    private int screenNum = 0; // 현재 선택지 화면 번호

    AbstractCard previewCurse = new MK_99_Scholar();
    AbstractCard previewCard = new Apotheosis();

    ArrayList<AbstractCard> colorlessCards = new ArrayList<>();
    private float previewTimer = 0f;
    private int previewIdx = 0;

    public Event_DontBelieveInR() {
        super(NAME, DESCRIPTIONS[0], "reactkrResources/images/events/DontBelieveInR.png");

        // CardLibrary에 등록된 모든 카드를 순회합니다.
        for (Map.Entry<String, AbstractCard> entry : CardLibrary.cards.entrySet()) {
            AbstractCard card = entry.getValue();

            // 카드의 색상이 COLORLESS인 경우만 리스트에 추가합니다.
            if (card.color == AbstractCard.CardColor.COLORLESS) {
                if(card.rarity != AbstractCard.CardRarity.SPECIAL){
                    colorlessCards.add(card.makeCopy());
                    // 카드의 복사본(makeCopy)을 넣는 것이 안전합니다.
                }
            }
        }

        // 첫 번째 선택지: 쿠로카
        imageEventText.setDialogOption(OPTIONS[0], previewCurse);
        // 두 번째 선택지: 로로카
        imageEventText.setDialogOption(OPTIONS[1], previewCard);
        // 세 번째 선택지: 마지또모
        imageEventText.setDialogOption(OPTIONS[2]);
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (screenNum) {
            case 0:
                if (buttonPressed == 0) { // 첫 번째 선택지 클릭 시
                    int incValue = 96 - AbstractDungeon.player.maxHealth;
                    AbstractDungeon.player.increaseMaxHp(incValue, true);

                    AbstractCard c = previewCurse.makeCopy();

                    // Settings.WIDTH / 2.0F 를 기준으로 x좌표를 조금씩 산개시킴
                    float xPos = (float) Settings.WIDTH * 0.5f;
                    float yPos = (float) Settings.HEIGHT / 2.0f;

                    AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, xPos, yPos));

                    this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                    this.imageEventText.updateDialogOption(0, OPTIONS[3]);
                    this.imageEventText.clearRemainingOptions();
                    screenNum = 1;
                } else if (buttonPressed == 1) { // 두 번째 선택지 클릭 시
                    int decValue = AbstractDungeon.player.maxHealth - 66;
                    AbstractDungeon.player.decreaseMaxHealth(decValue);

                    int randomIndex = AbstractDungeon.eventRng.random(0, colorlessCards.size() - 1);

                    previewCard = colorlessCards.get(randomIndex);

                    AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(previewCard, Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f));

                    this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                    this.imageEventText.updateDialogOption(0, OPTIONS[3]);
                    this.imageEventText.clearRemainingOptions();
                    screenNum = 1;
                } else { // 세 번째 클릭 시
                    AbstractDungeon.gridSelectScreen.open(
                            CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck.getPurgeableCards()),
                            2,
                            "[변화] 변화시킬 카드 2장을 선택하세요",
                            false,
                            false,
                            false,
                            false);

                    this.imageEventText.loadImage("reactkrResources/images/events/DontBelieveInR_M.png");
                    this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
                    this.imageEventText.updateDialogOption(0, OPTIONS[3]);
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
        return AbstractDungeon.player.maxHealth < 96 &&
                AbstractDungeon.player.maxHealth > 66 &&
                AbstractDungeon.player.chosenClass.equals(Kuroka.Enums.THE_KUROKA);
    }

    public void update() {
        super.update();
        previewTimer += Gdx.graphics.getDeltaTime();
        if (previewTimer > 1.0f) {
            previewTimer = 0f;
            previewIdx = (previewIdx + 1) % colorlessCards.size();
            this.previewCard = colorlessCards.get(previewIdx);
        }
        if (!AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            List<String> transCards = new ArrayList();
            List<String> obtainedCards = new ArrayList();
            if (AbstractDungeon.gridSelectScreen.selectedCards.size() == 2) {
                AbstractCard c = (AbstractCard) AbstractDungeon.gridSelectScreen.selectedCards.get(0);
                AbstractDungeon.player.masterDeck.removeCard(c);
                transCards.add(c.cardID);
                AbstractDungeon.transformCard(c, false, AbstractDungeon.miscRng);
                AbstractCard newCard1 = AbstractDungeon.getTransformedCard();
                obtainedCards.add(newCard1.cardID);
                AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(newCard1, (float) Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH / 2.0F - 20.0F * Settings.scale, (float) Settings.HEIGHT / 2.0F));
                c = (AbstractCard) AbstractDungeon.gridSelectScreen.selectedCards.get(1);
                AbstractDungeon.player.masterDeck.removeCard(c);
                transCards.add(c.cardID);
                AbstractDungeon.transformCard(c, false, AbstractDungeon.miscRng);
                AbstractCard newCard2 = AbstractDungeon.getTransformedCard();
                obtainedCards.add(newCard2.cardID);
                AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(newCard2, (float) Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH / 2.0F + 20.0F * Settings.scale, (float) Settings.HEIGHT / 2.0F));
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                //logMetricTransformCardsAtCost("Event_DontBelieveInR", "Transformed Cards", transCards, obtainedCards, this.cleanUpCost);
            }
        }
    }
}
