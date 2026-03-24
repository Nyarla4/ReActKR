package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Mayo;

import java.util.ArrayList;

import static reactkr.ModFile.makeID;

public class MM_43_tempAimedDraw extends AbstractEasyCard_Mayo {
    public final static String ID = makeID("tempAimedDraw");

    public MM_43_tempAimedDraw() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 4;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                if (!p.hand.isEmpty()) {
                    int discardCount = Math.min(p.hand.group.size(), 2);
                    ArrayList<AbstractCard> targets = new ArrayList<>();
                    for (int i = 0; i < discardCount; i++) {
                        targets.add(p.hand.group.get(i));
                    }

                    for (AbstractCard c : targets) {
                        AbstractDungeon.actionManager.addToTop(
                                new com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction(c, p.hand)
                        );
                    }
                }
                this.isDone = true;
            }
        });
        this.addToBot(new DrawCardAction(magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}
