package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.powers.mayo.MM_03_EvasionPower;

import static reactkr.ModFile.makeID;

public class MM_46_EternalEvasion extends AbstractEasyCard_Mayo {
    public final static String ID = makeID("EternalEvasion");

    public MM_46_EternalEvasion() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 5;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        MM_03_EvasionPower pow = new MM_03_EvasionPower(p, 0);
        pow.ETERNAL_AMOUNT = magicNumber;
        pow.updateDescription();
        if(p.hasPower(MM_03_EvasionPower.POWER_ID)){
            pow = (MM_03_EvasionPower)p.getPower(MM_03_EvasionPower.POWER_ID);
            pow.ETERNAL_AMOUNT += magicNumber;
        }
        else{
            addToBot(new ApplyPowerAction(p, p, pow));
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }
}
