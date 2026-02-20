package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.powers.kuroka.MK_06_MajinaiAmplify_Power;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.applyToSelf;

public class MK_27_MajinaiAmplify extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("MajinaiAmplify");
    // intellij stuff skill, self, uncommon, , , , , ,

    public MK_27_MajinaiAmplify() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new MK_06_MajinaiAmplify_Power(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }
}
