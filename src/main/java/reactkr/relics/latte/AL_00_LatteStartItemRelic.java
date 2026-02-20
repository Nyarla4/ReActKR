package reactkr.relics.latte;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import reactkr.Latte;
import reactkr.cards.subcards.AL_00_1_SelectIceAmericano;
import reactkr.cards.subcards.AL_00_2_SelectMayo;
import reactkr.relics.AbstractEasyRelic;

import static reactkr.ModFile.makeID;

public class AL_00_LatteStartItemRelic extends AbstractEasyRelic {
    public static final String ID = makeID("LatteStartItemRelic");
    private boolean isSelecting = false;
    private boolean initialized = false; // UI 로딩 확인용

    public AL_00_LatteStartItemRelic() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, Latte.Enums.LATTE_COLOR);
    }

    AbstractCard selector1 = new AL_00_1_SelectIceAmericano();
    AbstractCard selector2 = new AL_00_2_SelectMayo();

    @Override
    public void update() {
        super.update();

        // 1. 아직 초기화 전이고, 던전/UI가 준비되었다면 선택창 열기
        if (!initialized && AbstractDungeon.isScreenUp == false && AbstractDungeon.currMapNode != null) {
            openChoiceScreen();
            initialized = true;
            isSelecting = true;
        }

        // 카드가 선택되었는지 실시간 감시
        if (this.isSelecting && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            AbstractCard selected = AbstractDungeon.gridSelectScreen.selectedCards.get(0);

            // 1. 선택한 카드에 따라 실제 유물 지급
            if (selected.cardID.equals(selector2.cardID)) {
                replaceWith(new AL_00_2_PaisenRelic()); // 기존에 만드신 유물
            } else {
                replaceWith(new AL_00_1_IceAmeriacanoRelic()); // 다른 유물
            }

            // 2. 선택 완료 처리
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            this.isSelecting = false;
        }
    }

    private void replaceWith(AbstractRelic newRelic) {
        int index = AbstractDungeon.player.relics.indexOf(this);
        // 현재 이 상자를 지우고 그 자리에 새 유물을 넣습니다.
        newRelic.instantObtain(AbstractDungeon.player, index, true);
        this.flash();
    }

    private void openChoiceScreen() {
        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        group.addToTop(selector1);
        group.addToTop(selector2);

        // UI가 준비된 시점이므로 안전하게 호출 가능
        AbstractDungeon.gridSelectScreen.open(group, 1, "시작 유물을 선택하세요.", false);
    }
}
