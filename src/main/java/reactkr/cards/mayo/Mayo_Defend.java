package reactkr.cards.mayo;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Mayo;

import static reactkr.ModFile.makeID;

public class Mayo_Defend extends AbstractEasyCard_Mayo {
    public final static String ID = makeID("Mayo_Defend");
    // intellij stuff skill, self, basic, , ,  5, 3, , 

    public Mayo_Defend() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        baseBlock = 5;
        tags.add(CardTags.STARTER_DEFEND);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }
}