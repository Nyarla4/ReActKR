package reactkr.cards.subcards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static reactkr.ModFile.makeID;

public class MM_08_Deer02 extends MM_08_AbstractCooking {
    public final static String ID = makeID("CookedDeer");
    // intellij stuff skill, self, uncommon, , , , , ,

    public MM_08_Deer02() {
        super(ID, 1, CardType.SKILL, 12, 1, new MM_08_Deer03());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        this.addToBot(new DrawCardAction(magicNumber));
    }

    @Override
    protected int upgradeBlockAmount(){
        return 6;
    }
}
