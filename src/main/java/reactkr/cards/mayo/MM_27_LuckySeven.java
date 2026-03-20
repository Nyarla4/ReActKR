package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static reactkr.ModFile.makeID;

public class MM_27_LuckySeven extends AbstractAimedCard {
    public final static String ID = makeID("LuckySeven");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,
    private static final Logger logger = LogManager.getLogger(MM_27_LuckySeven.class.getName());
    public MM_27_LuckySeven() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 6;
        baseSecondDamage = secondDamage = 8;
        baseMagicNumber = magicNumber = 2;
    }

    @Override
    public void normalUse(AbstractPlayer p, AbstractMonster m) {
        int rand = AbstractDungeon.cardRandomRng.random(1, 7);
        logger.info(rand);
        if (rand == 1) {
            finalDamage *= magicNumber;
        }
        addToBot(new DamageAction(m, new DamageInfo(p, finalDamage)));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}