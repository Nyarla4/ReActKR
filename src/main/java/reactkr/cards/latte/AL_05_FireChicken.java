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

public class AL_05_FireChicken extends AbstractMorningCard {
    public final static String ID = makeID("FireChicken");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public AL_05_FireChicken() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 6;
        baseMagicNumber = magicNumber = 3;
        baseSecondDamage = secondDamage = 5;
        initializeSummaryPreview();
    }

    @Override
    public void morningUse(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        if (isFirst) {
            atb(new DamageAction(p, new DamageInfo(p, magicNumber, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
            atb(new DamageAction(m, new DamageInfo(p, secondDamage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
        }
    }

    @Override
    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(-1);
        upgradeSecondDamage(3);
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