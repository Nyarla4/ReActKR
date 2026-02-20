package reactkr.cards.latte;

import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.actions.PlayRandomCardAction;
import reactkr.cards.AbstractEasyCard_Latte;
import reactkr.powers.latte.AL_01_NoBadWordPower;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.applyToSelf;

public class AL_10_TtediAdvice extends AbstractEasyCard_Latte {
    public final static String ID = makeID("TtediAdvice");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public AL_10_TtediAdvice() {
        super(ID, 3, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        initializeSummaryPreview();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i < this.magicNumber; ++i) {
            this.addToBot(new PlayRandomCardAction(AbstractDungeon.getCurrRoom().monsters.getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng), false));
        }
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