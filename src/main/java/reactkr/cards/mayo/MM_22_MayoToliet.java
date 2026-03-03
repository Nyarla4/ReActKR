package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import reactkr.cards.AbstractEasyCard_Mayo;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.applyToSelf;

public class MM_22_MayoToliet extends AbstractEasyCard_Mayo {
    public final static String ID = makeID("MayoToliet");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public MM_22_MayoToliet() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.ALL);
        baseMagicNumber = magicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new WeakPower(p, 1, false));
        for(AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            addToBot(new ApplyPowerAction(mo, p, new VulnerablePower(m, 1, false)));
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}