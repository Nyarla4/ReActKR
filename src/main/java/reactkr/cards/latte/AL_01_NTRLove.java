package reactkr.cards.latte;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.atb;

public class AL_01_NTRLove extends AbstractMorningCard {
    public final static String ID = makeID("NTRLove");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public AL_01_NTRLove() {
        super(ID, 3, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 5;
        baseSecondMagic = secondMagic = 3;
        initializeSummaryPreview();
    }

    @Override
    public void morningUse(AbstractPlayer p, AbstractMonster m) {
        damage = magicNumber;
        atb(new DamageAction(p, new DamageInfo(p, magicNumber, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
        if (isFirst) {
            damage += secondMagic;
            atb(new DamageAction(p, new DamageInfo(p, secondMagic, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
        }

        atb(new DamageAction(m, new DamageInfo(p, damage * (upgraded ? 2 : 1), DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public void upp() {

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