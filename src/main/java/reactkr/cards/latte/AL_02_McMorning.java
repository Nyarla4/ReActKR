package reactkr.cards.latte;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Latte;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.atb;

public class AL_02_McMorning extends AbstractEasyCard_Latte {
    public final static String ID = makeID("McMorning");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public AL_02_McMorning() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 5;
        baseSecondMagic = secondMagic = 3;
        initializeSummaryPreview();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DamageAction(p, new DamageInfo(p, magicNumber, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
        atb(new DrawCardAction(secondMagic));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(-3);
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