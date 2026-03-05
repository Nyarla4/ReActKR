package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.cards.AbstractEasyCard_Mayo;

import static reactkr.ModFile.makeID;

public class MM_14_EffortGenious extends AbstractEasyCard_Mayo {
    public final static String ID = makeID("EffortGenious");
    // intellij stuff skill, self, uncommon, , , , , ,
    private static final Logger logger = LogManager.getLogger(MM_14_EffortGenious.class.getName());

    public MM_14_EffortGenious() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 7;
        baseSecondDamage = secondDamage = 9;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int randomValue = AbstractDungeon.cardRandomRng.random(damage, secondDamage);
        if (this.timesUpgraded < 3) {
            addToBot(new DamageAction(m, new DamageInfo(p, randomValue, DamageInfo.DamageType.NORMAL)));
        } else {
            this.addToBot(new DamageAllEnemiesAction(p,
                    DamageInfo.createDamageMatrix(randomValue, true),
                    DamageInfo.DamageType.NORMAL,
                    AbstractGameAction.AttackEffect.SLASH_HEAVY
            ));
        }
    }

    public void upgrade() {
        this.upgradeDamage(3);//4+(강화수치-1)
        this.upgradeSecondDamage(3);
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = cardStrings.NAME + "+" + this.timesUpgraded;
        this.initializeTitle();
        if (this.timesUpgraded >= 3) {
            this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
            this.target = CardTarget.ALL_ENEMY;
        }
    }

    @Override
    public void upp() {

    }

    public boolean canUpgrade() {
        return true;
    }
}
