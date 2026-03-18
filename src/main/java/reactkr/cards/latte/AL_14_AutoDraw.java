package reactkr.cards.latte;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.actions.AL_11_SpinCardAction;
import reactkr.cards.AbstractEasyCard_Latte;

import static reactkr.ModFile.makeID;

public class AL_14_AutoDraw extends AbstractAutoCard {
    public final static String ID = makeID("AutoDraw");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    private static final Logger logger = LogManager.getLogger(AL_14_AutoDraw.class.getName());

    public AL_14_AutoDraw() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, true);
        baseMagicNumber = magicNumber = 2;
        this.exhaust = true;
        initializeSummaryPreview();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
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
    public void AutoAct() {
        this.addToBot(new DrawCardAction(magicNumber));
    }
}