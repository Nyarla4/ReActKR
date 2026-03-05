package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import reactkr.cards.AbstractEasyCard_Mayo;

import static reactkr.ModFile.makeID;

public class MM_00_WarningShot extends AbstractAimedCard {
    public final static String ID = makeID("WarningShot");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public MM_00_WarningShot() {
        super(ID, 0, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = damage = 6;
        baseSecondDamage = secondDamage = 8;
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void normalUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, finalDamage)));
    }

    @Override
    public void aimedUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, magicNumber, false)));
    }

    @Override
    public int basicDepletion() {
        return -1;
    }

    @Override
    public void upp() {
        upgradeDamage(3);
        upgradeSecondDamage(3);
        upgradeMagicNumber(1);
    }

    @Override
    protected boolean useBullet() {
        return true;
    }
}