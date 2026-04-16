package reactkr.cards.subcards;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static reactkr.ModFile.makeID;

public class MM_08_Wolf01 extends MM_08_AbstractCooking {
    public final static String ID = makeID("Wolf");

    public MM_08_Wolf01() {
        super(ID, 1, CardType.SKILL, 8,3,new MM_08_Wolf02());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(p, new DamageInfo(p, magicNumber)));
        blck();
    }

    @Override
    protected int upgradeBlockAmount() {
        return 4;
    }
}
