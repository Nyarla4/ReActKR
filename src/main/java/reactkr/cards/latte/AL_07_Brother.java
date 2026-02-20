package reactkr.cards.latte;

import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Latte;
import reactkr.stances.latte.MochaStance;

import static reactkr.ModFile.makeID;

public class AL_07_Brother extends AbstractEasyCard_Latte {
    public final static String ID = makeID("Brother");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public AL_07_Brother() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        initializeSummaryPreview();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new ChangeStanceAction(new MochaStance()));
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
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