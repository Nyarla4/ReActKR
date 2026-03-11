package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.powers.mayo.MM_03_EvasionPower;

import static reactkr.ModFile.makeID;

public class MM_49_Obscuration2 extends AbstractEasyCard_Mayo {
    public final static String ID = makeID("Obscuration2");

    public MM_49_Obscuration2() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        MM_03_EvasionPower pow = (MM_03_EvasionPower) p.getPower(MM_03_EvasionPower.POWER_ID);
        int amount = pow.amount;
        addToBot(new ApplyPowerAction(p, p, new MM_03_EvasionPower(p, -amount)));
        addToBot(new GainBlockAction(p, amount));
    }

    @Override
    public void upp() {
        this.selfRetain = true;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (!super.canUse(p, m)) {
            return false;
        }
        if (!p.hasPower(MM_03_EvasionPower.POWER_ID)){
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return false;
        }
        return p.getPower(MM_03_EvasionPower.POWER_ID).amount > 0;
    }
}
