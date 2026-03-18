package reactkr.cards.latte;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.actions.AL_11_SpinCardAction;

import static reactkr.ModFile.makeID;

public class AL_13_RetainBlock extends AbstractRetainCard {
    public final static String ID = makeID("RetainBlock");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    private static final Logger logger = LogManager.getLogger(AL_13_RetainBlock.class.getName());

    public AL_13_RetainBlock() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = block = 10;
        this.exhaust = true;
        initializeSummaryPreview();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        this.addToBot(new AL_11_SpinCardAction(this, 0.5F, 6));
    }

    @Override
    public void upp() {
        upgradeBlock(5);
    }

    @Override
    public void ContinuousAct() {
        blck();
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