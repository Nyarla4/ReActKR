package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Kuroka;

import static reactkr.ModFile.makeID;

public class MK_09_SnekoDragon extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("SnekoDragon");
    // intellij stuff skill, none, common, , , , , ,

    public MK_09_SnekoDragon() {
        super(ID, 1, AbstractCard.CardType.SKILL, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.NONE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int randomDraw = AbstractDungeon.cardRandomRng.random(3) + 1;
        for (int i = 0; i < randomDraw; i++) {
            addToBot(new DrawCardAction(1));
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    if (!p.hand.isEmpty()) {
                        AbstractCard target = p.hand.getTopCard();
                        int randomValue = AbstractDungeon.cardRandomRng.random(3);
                        target.setCostForTurn(randomValue);
                    }
                    this.isDone = true;
                }
            });
        }
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}
