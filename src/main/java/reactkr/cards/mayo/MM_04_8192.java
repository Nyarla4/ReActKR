package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.relics.SiahRelic;

import static reactkr.ModFile.makeID;

public class MM_04_8192 extends AbstractEasyCard_Mayo {
    public final static String ID = makeID("8192");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,
    private static final Logger logger = LogManager.getLogger(MM_04_8192.class.getName());

    public MM_04_8192() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 3;
        baseMagicNumber = magicNumber = 13;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //시아 유물 있으면 3/4, 없으면 1/2
        int perc = p.hasRelic(SiahRelic.ID) ? 75 : 50;

        for (int i = 0; i < magicNumber; i++) {
            int roll = AbstractDungeon.cardRandomRng.random(1, 100);
            logger.info(roll);
            if (roll < perc) {
                dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
            } else {
                break;
            }
        }
    }

    @Override
    public void upp() {
        upgradeDamage(1);
    }
}