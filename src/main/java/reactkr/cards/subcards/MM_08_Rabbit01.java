package reactkr.cards.subcards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static reactkr.ModFile.makeID;

public class MM_08_Rabbit01 extends MM_08_AbstractCooking {
    public final static String ID = makeID("Rabbit");

    public MM_08_Rabbit01() {
        super(ID, 0, CardType.SKILL, 3,new MM_08_Rabbit02());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    protected int upgradeBlockAmount() {
        return 3;
    }
}
