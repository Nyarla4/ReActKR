package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import reactkr.cards.AbstractEasyCard_Mayo;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.applyToSelf;

public class MM_15_HapticSuit extends AbstractEasyCard_Mayo {
    public final static String ID = makeID("HapticSuit");
    // intellij stuff skill, self, uncommon, , , , , ,

    public MM_15_HapticSuit() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 1;
        baseSecondMagic = secondMagic = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new VulnerablePower(p, magicNumber, false));
        addToBot(new DrawCardAction(secondMagic));
    }

    @Override
    public void upp() {
        upgradeSecondMagic(1);
    }

    public boolean canUpgrade() {
        return true;
    }
}
