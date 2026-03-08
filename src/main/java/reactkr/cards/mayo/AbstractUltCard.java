package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.powers.mayo.MM_01_UltPower;

public abstract class AbstractUltCard extends AbstractEasyCard_Mayo {

    public AbstractUltCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(!p.hasPower(MM_01_UltPower.POWER_ID)){
            addToBot(new ApplyPowerAction(p, p, new MM_01_UltPower(p, 1), 1));
        }
        normalUse(p, m);
        if (p.hasPower(MM_01_UltPower.POWER_ID)) {
            if (p.getPower(MM_01_UltPower.POWER_ID).amount >= MM_01_UltPower.maxAmount) {
                if(ultUse(p, m)) {
                    addToBot(new RemoveSpecificPowerAction(p, p, MM_01_UltPower.POWER_ID));
                }
            }
        }
    }

    abstract void normalUse(AbstractPlayer p, AbstractMonster m);

    abstract boolean ultUse(AbstractPlayer p, AbstractMonster m);

    protected boolean ultCharged() {
        AbstractPlayer p = AbstractDungeon.player;
        if (!p.hasPower(MM_01_UltPower.POWER_ID)) {
            return false;
        }
        return p.getPower(MM_01_UltPower.POWER_ID).amount >= MM_01_UltPower.maxAmount;
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = ultCharged() ?
                AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy() :
                AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
    }
}
