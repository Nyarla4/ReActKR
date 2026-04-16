package reactkr.cards.subcards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static reactkr.ModFile.makeID;

public class MM_08_Rabbit02 extends MM_08_AbstractCooking {
    public final static String ID = makeID("CookedRabbit");

    public MM_08_Rabbit02() {
        super(ID, 0, CardType.SKILL, 6, new MM_08_Rabbit03());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public int upgradeBlockAmount() {
        return 6;
    }
}
