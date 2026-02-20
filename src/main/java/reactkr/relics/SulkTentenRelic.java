package reactkr.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.NeowsLament;

import static reactkr.ModFile.makeID;

public class SulkTentenRelic extends AbstractEasyRelic {
    public static final String ID = makeID("SulkTentenRelic");

    public SulkTentenRelic() {
        super(ID, AbstractRelic.RelicTier.SPECIAL, AbstractRelic.LandingSound.FLAT, null);
        this.counter = 3;
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void atBattleStart() {
        if (this.counter > 0) {
            --this.counter;
            if (this.counter == 0) {
                this.setCounter(-2);
                this.description = this.DESCRIPTIONS[1];
                this.tips.clear();
                this.tips.add(new PowerTip(this.name, this.description));
                this.initializeTips();
            }

            this.flash();

            AbstractPlayer p = AbstractDungeon.player;
            for(AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                m.currentHealth = 1;
                addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, 1)));
            }

            this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        }

    }

    public void setCounter(int setCounter) {
        this.counter = setCounter;
        if (setCounter <= 0) {
            this.usedUp();
        }

    }

    public AbstractRelic makeCopy() {
        return new NeowsLament();
    }
}