package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.powers.mayo.MM_03_EvasionPower;

import static reactkr.ModFile.makeID;

public class MM_00_2_Rolling extends AbstractEasyCard_Mayo {
    public final static String ID = makeID("Rolling");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public MM_00_2_Rolling() {
        super(ID, 0, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        baseMagicNumber = magicNumber = 15;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(6);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new MM_03_EvasionPower(p, magicNumber)));
    }
}