package reactkr.cards.curse;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import reactkr.cards.AbstractEasyCard;

import static reactkr.ModFile.makeID;

public class CURSE_02_LesserKusa extends AbstractEasyCard {
    public final static String ID = makeID("LesserKusa");
    // intellij stuff curse, none, curse, , , , , ,

    public CURSE_02_LesserKusa() {
        super(ID, 1, CardType.STATUS, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToTop(new ApplyPowerAction(m, m, new StrengthPower(m, 1), 1));
    }

    public void upp() {

    }
}