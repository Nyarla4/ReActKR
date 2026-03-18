package reactkr.cards.latte;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.actions.AL_11_SpinCardAction;
import reactkr.cards.AbstractEasyCard_Latte;

import java.util.ArrayList;

import static reactkr.ModFile.makeID;

public class AL_11_CardBack extends AbstractEasyCard_Latte {
    public final static String ID = makeID("CardBack");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    private ArrayList<AbstractCard> cardPools = new ArrayList<>();
    public AL_11_CardBack() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        initializeSummaryPreview();
        cardPools.add(new LimitBreak());
        cardPools.add(new Inflame());
        cardPools.add(new Flex());
        cardPools.add(new HeavyBlade());
        cardPools.add(new Armaments());
        cardPools.add(new SpotWeakness());
        cardPools.add(new Offering());
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            AbstractCard card = cardPools.get(AbstractDungeon.cardRandomRng.random(cardPools.size() - 1));

            // [중요] 원본 카드를 그대로 주면 안 되고, 반드시 복사본(makeStatEquivalentCopy)을 생성해야 함
            // 그래야 원본 데이터 구조가 오염되지 않습니다.
            AbstractCard copy = card.makeStatEquivalentCopy();

            // 3. [전이 수행] 플레이어의 덱(혹은 손패)에 추가하는 흐름 실행
            // '전투 중에만' 덱에 섞어넣고 싶을 때 사용하는 표준 메서드
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(copy, 1));
        }
        this.addToBot(new AL_11_SpinCardAction(this, 0.5F, 3));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
        for (AbstractCard card : cardPools){
            card.upgrade();
        }
    }

    @Override
    protected int getPreviewDescriptionIndex() {
        return 0;
    }

    @Override
    protected int getPreviewUpgradeDescriptionIndex() {
        return -1;
    }
}