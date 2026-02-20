package reactkr.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.powers.ReactKRRangersPower;
import reactkr.powers.uru.MU_00_RainPower;

import static reactkr.ModFile.makeID;

public class UruCard extends AbstractEasyCard {
    public final static String ID = makeID("Uru");

    public UruCard() {
        super(ID, 3, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ALL_ENEMY, CardColor.COLORLESS);
        baseDamage = damage = 3;
        baseMagicNumber = magicNumber = 8;
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (AbstractDungeon.player.hasPower(ReactKRRangersPower.POWER_ID)) {
            this.rawDescription = this.cardStrings.EXTENDED_DESCRIPTION[0];
        }
        this.initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i < this.magicNumber; ++i) {
            this.addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.NONE));
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(4);
    }

    @Override
    public void triggerOnGlowCheck() {
        if(AbstractDungeon.player.hasPower(MU_00_RainPower.POWER_ID)){
            setCostForTurn(AbstractDungeon.player.hasPower(ReactKRRangersPower.POWER_ID)?1:2);
        }
    }
}
