package reactkr.cards.latte;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import reactkr.cards.AbstractEasyCard_Latte;
import reactkr.stances.latte.MochaStance;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.atb;

public class AL_06_Sekuhara extends AbstractEasyCard_Latte {
    public final static String ID = makeID("Sekuhara");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public AL_06_Sekuhara() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 2;
        baseDamage = damage = 5;
        initializeSummaryPreview();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ApplyPowerAction(m, p, new VulnerablePower(m, magicNumber, false)));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
    }

    @Override
    public void upp() {
        upgradeDamage(5);
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
        return MochaStance.STANCE_ID;
    }

    @Override
    protected String getForbiddenMessage() {
        return MochaStance.CantUseMessage;
    }
}