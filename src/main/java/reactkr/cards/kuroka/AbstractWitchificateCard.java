package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.powers.kuroka.MK_01_Majinai_Power;
import reactkr.powers.kuroka.MK_04_Witchification_Power;

public abstract class AbstractWitchificateCard extends AbstractEasyCard_Kuroka {

    protected boolean witchificated;
    // 변수명을 명확히 하고, 인스턴스 변수가 아닌 static으로 '복제 중' 상태를 공유합니다.
    private static boolean isCloning = false;

    private static final Logger logger = LogManager.getLogger(AbstractWitchificateCard.class.getName());

    public AbstractWitchificateCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target);
        witchificated = false;
    }

    abstract void Witchification();

    @Override
    public void applyPowers(){
        super.applyPowers();
        witchificated = AbstractDungeon.player.hasPower(MK_04_Witchification_Power.POWER_ID);
        Witchification();
        // 로그를 추가해서 데이터가 살아있는지 확인하세요.
        if (this.rawDescription == null) {
            logger.info("에러 방지: "+this.cardID+" rawDescription이 null입니다!");
            this.rawDescription = ""; // 임시 조치
        }
        initializeDescription();
        initializeWitchPreview();
    }

    @Override
    public void upgrade() {
        super.upgrade();
        initializeWitchPreview();
    }

    public void initializeWitchPreview() {
        // 1. 만약 지금 이미 다른 카드를 복제하는 중이라면, 더 이상 진행하지 않고 멈춥니다.
        if (isCloning) return;

        // 2. 이제부터 복제를 시작한다고 문을 잠급니다.
        isCloning = true;

        try {
            // 3. 복사본 생성 (이 메서드 내부에서 new MK_06...이 호출되지만, isCloning이 true라 1번에서 걸러짐)
            AbstractWitchificateCard preview = (AbstractWitchificateCard) this.makeStatEquivalentCopy();

            // 4. 복사본 상태 설정
            preview.witchificated = !this.witchificated;

            preview.Witchification();

            preview.initializeDescription();

            this.cardsToPreview = preview;
        } finally {
            // 5. 작업이 끝났으므로(성공하든 에러가 나든) 다시 문을 열어줍니다.
            isCloning = false;
        }
    }
}
