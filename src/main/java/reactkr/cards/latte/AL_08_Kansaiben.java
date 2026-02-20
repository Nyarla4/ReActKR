package reactkr.cards.latte;

import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Latte;
import reactkr.stances.latte.AotoStance;

import static reactkr.ModFile.makeID;

public class AL_08_Kansaiben extends AbstractEasyCard_Latte {
    public final static String ID = makeID("Kansaiben");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public AL_08_Kansaiben() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        initializeSummaryPreview();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new ChangeStanceAction(new AotoStance()));
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