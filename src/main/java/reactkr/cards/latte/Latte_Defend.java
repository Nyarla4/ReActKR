package reactkr.cards.latte;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Latte;

import static reactkr.ModFile.makeID;

public class Latte_Defend extends AbstractEasyCard_Latte {
    public final static String ID = makeID("Latte_Defend");
    // intellij stuff skill, self, basic, , ,  5, 3, , 

    public Latte_Defend() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        baseBlock = 10;
        tags.add(CardTags.STARTER_DEFEND);
        initializeSummaryPreview();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void upp() {
        upgradeBlock(6);
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