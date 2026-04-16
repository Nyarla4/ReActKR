package reactkr.cards.subcards;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.actions.TransformCardInHandAction;
import reactkr.cards.AbstractEasyCard_Mayo;

import static reactkr.ModFile.makeID;

public class MM_08_Wolf02 extends MM_08_AbstractCooking {
    public final static String ID = makeID("CookedWolf");

    public MM_08_Wolf02() {
        super(ID, 1, CardType.SKILL, 16, 3, new MM_08_Wolf03());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(p, new DamageInfo(p, magicNumber)));
        blck();
    }

    @Override
    protected int upgradeBlockAmount() {
        return 8;
    }
}
