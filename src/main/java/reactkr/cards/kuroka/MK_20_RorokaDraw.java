package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Kuroka;

import static reactkr.ModFile.makeID;
import static reactkr.patches.RorokaTag.ROROKA;

public class MK_20_RorokaDraw extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("RorokaDraw");
    // intellij stuff attack, enemy, uncommon, X, , , , ,

    public MK_20_RorokaDraw() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        tags.add(ROROKA);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
// 1. 먼저 카드를 1장 뽑습니다.
        this.addToBot(new DrawCardAction(2));

        // 2. 뽑힌 카드를 확인하는 액션 추가
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                // 손패의 가장 마지막 카드(방금 뽑은 카드)를 가져옴
                if (!AbstractDungeon.player.hand.isEmpty()) {
                    AbstractCard drawnCard = AbstractDungeon.player.hand.getTopCard();

                    // 3. 특정 태그(PARRY_CARD)가 있는지 확인
                    if (drawnCard.hasTag(ROROKA)) {
                        //drawnCard.flash(com.badlogic.gdx.graphics.Color.GOLD.cpy()); // 효과 강조

                        // 4. magicNumber만큼 추가 드로우 실행
                        // addToTop을 사용해야 이 액션이 현재 큐의 가장 처음에 배치되어 즉시 실행됩니다.
                        this.addToTop(new DrawCardAction(magicNumber));
                    }
                }
                this.isDone = true;
            }
        });
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
