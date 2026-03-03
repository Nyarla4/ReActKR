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
                for (AbstractCard c : DrawCardAction.drawnCards) {

                    // 3. [판단 흐름] 각각의 카드가 ROROKA 태그를 가졌는지 검사
                    if (c.hasTag(ROROKA)) {
                        // c.flash(com.badlogic.gdx.graphics.Color.GOLD.cpy()); // 효과 강조

                        // 4. [수행 과정] 태그가 있다면 magicNumber만큼 추가 드로우
                        // addToTop을 쓰면 현재 진행 중인 드로우 흐름 바로 다음에 최우선으로 끼어듭니다.
                        AbstractDungeon.actionManager.addToTop(new DrawCardAction(AbstractDungeon.player, magicNumber));
                    }
                }
                // 액션 종료 처리
                this.isDone = true;
            }
        });
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
