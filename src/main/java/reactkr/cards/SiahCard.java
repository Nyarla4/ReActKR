package reactkr.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.powers.ReactKRRangersPower;
import reactkr.powers.siah.AS_00_MPower;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.applyToSelf;
import static reactkr.util.Wiz.atb;

public class SiahCard extends AbstractEasyCard {
    public final static String ID = makeID("Siah");

    public SiahCard() {
        super(ID, 2, CardType.POWER, CardRarity.SPECIAL, CardTarget.NONE, CardColor.COLORLESS);
        baseMagicNumber = magicNumber = 3;
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (AbstractDungeon.player.hasPower(ReactKRRangersPower.POWER_ID)) {
            this.rawDescription = this.cardStrings.EXTENDED_DESCRIPTION[0];
        }
        this.initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!p.hasPower(ReactKRRangersPower.POWER_ID)) {
            atb(new DamageAction(p, new DamageInfo(p, magicNumber, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
        }
        applyToSelf(new AS_00_MPower(p));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(-1);
        updateCost(-1);
    }

}
