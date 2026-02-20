package reactkr.cards.latte;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Latte;
import reactkr.stances.latte.LatteStance;

import static reactkr.ModFile.makeID;

public class AL_04_TtediFault extends AbstractEasyCard_Latte {
    public final static String ID = makeID("TtediFault");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public AL_04_TtediFault() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = block = 15;
        baseMagicNumber = magicNumber = 2;
        initializeSummaryPreview();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new DrawCardAction(magicNumber));
        addToBot(new ChangeStanceAction(new LatteStance()));
    }

    @Override
    public void upp() {
        upgradeBlock(5);
    }

    @Override
    protected int getPreviewDescriptionIndex() {
        return 0;
    }

    @Override
    protected int getPreviewUpgradeDescriptionIndex() {
        return -1;
    }

    @Override
    protected String getForbiddenStanceID() {
        return null;
    }

    @Override
    protected String getForbiddenMessage() {
        return null;
    }
}