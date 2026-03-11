package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.actions.CardChangeAction;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.cards.subcards.MM_50_NowYouNezming;
import reactkr.powers.mayo.MM_03_EvasionPower;

import static reactkr.ModFile.makeID;

public class MM_50_SubPresent extends AbstractEasyCard_Mayo {
    public final static String ID = makeID("SubPresent");

    public MM_50_SubPresent() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        cardsToPreview = new MM_50_NowYouNezming();
        baseDamage = damage = 4;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        addToBot(new CardChangeAction(p.hand, cardsToPreview.makeStatEquivalentCopy(), 1));
    }

    @Override
    public void upp() {
        upgradeDamage(2);
        cardsToPreview.upgrade();
    }
}
