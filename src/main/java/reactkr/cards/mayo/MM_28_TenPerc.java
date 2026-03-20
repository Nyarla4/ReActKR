package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static reactkr.ModFile.makeID;

public class MM_28_TenPerc extends AbstractAimedCard {
    public final static String ID = makeID("TenPerc");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,
    private static final Logger logger = LogManager.getLogger(MM_28_TenPerc.class.getName());
    public MM_28_TenPerc() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 8;
        baseSecondDamage = secondDamage = 12;
        baseMagicNumber = magicNumber = 2;
    }

    @Override
    public void normalUse(AbstractPlayer p, AbstractMonster m) {
        int rand1 = AbstractDungeon.cardRandomRng.random(1, 100);
        logger.info("감나빗 " + rand1);
        if (rand1 <= 10 && !isAimed()) {
            return;
        }
        int rand2 = AbstractDungeon.cardRandomRng.random(1, 100);
        logger.info("배 " + rand2);
        if (rand2 <= 10) {
            finalDamage *= magicNumber;
        }
        addToBot(new DamageAction(m, new DamageInfo(p, finalDamage)));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}