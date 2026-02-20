package reactkr.cards.curse;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import reactkr.cards.AbstractEasyCard;

import static reactkr.ModFile.makeID;

public class CURSE_01_Kusa extends AbstractEasyCard {
    public final static String ID = makeID("Kusa");
    // intellij stuff curse, none, curse, , , , , ,

    public CURSE_01_Kusa() {
        super(ID, -2, AbstractCard.CardType.CURSE, AbstractCard.CardRarity.CURSE, AbstractCard.CardTarget.NONE, CardColor.CURSE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.dontTriggerOnUseCard) {
            this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VulnerablePower(AbstractDungeon.player, 1, true), 1));
        }
    }

    public void triggerOnEndOfTurnForPlayingCard() {
        this.dontTriggerOnUseCard = true;
        AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(this, true));
    }

    public void upp() {

    }
}