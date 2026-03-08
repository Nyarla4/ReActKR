package reactkr.cards.mayo;

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
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ALL);
        baseMagicNumber = magicNumber = 4;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!p.hand.isEmpty()) {
            int discardCount = Math.min(p.hand.group.size(), 2);
            ArrayList<AbstractCard> discardTargets = new ArrayList<>();
            for (int i = 0; i < discardCount; i++) {
                discardTargets.add(p.hand.group.get(i));
            }
            for (AbstractCard c : discardTargets) {
                p.hand.moveToDiscardPile(c);
            }
        }
        this.addToBot(new DrawCardAction(magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}
