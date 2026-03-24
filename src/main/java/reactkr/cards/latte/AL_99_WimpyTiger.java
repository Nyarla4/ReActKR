package reactkr.cards.latte;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Latte;
import reactkr.powers.latte.AL_01_NoBadWordPower;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.applyToSelf;

public class AL_99_WimpyTiger extends AbstractEasyCard_Latte {
    public final static String ID = makeID("WimpyTiger");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public AL_99_WimpyTiger() {
        super(ID, 0, CardType.STATUS, CardRarity.SPECIAL, CardTarget.NONE);
        this.exhaust = true;
        initializeSummaryPreview();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }

    @Override
    protected int getPreviewDescriptionIndex() {
        return -1;
    }

    @Override
    protected int getPreviewUpgradeDescriptionIndex() {
        return -1;
    }
}