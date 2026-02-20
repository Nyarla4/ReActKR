package reactkr.cards.latte;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Latte;
import reactkr.powers.latte.AL_01_NoBadWordPower;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.applyToSelf;

public class AL_09_NoBadWord extends AbstractEasyCard_Latte {
    public final static String ID = makeID("NoBadWord");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public AL_09_NoBadWord() {
        super(ID, 1, CardType.POWER, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        initializeSummaryPreview();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new AL_01_NoBadWordPower(p, this.magicNumber));
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
    protected String getForbiddenStanceID() {
        return null;
    }

    @Override
    protected String getForbiddenMessage() {
        return null;
    }
}