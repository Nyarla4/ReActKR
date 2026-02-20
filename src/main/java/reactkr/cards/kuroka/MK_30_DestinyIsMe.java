package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.powers.kuroka.MK_07_Destiny_Power;

import static reactkr.ModFile.makeID;

public class MK_30_DestinyIsMe extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("DestinyIsMe");
    // intellij stuff skill, self, common, , , , , 1, 2

    public MK_30_DestinyIsMe() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 16;
        baseMagicNumber = magicNumber = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        this.addToBot(new ApplyPowerAction(p, p, new MK_07_Destiny_Power(p, magicNumber), 1));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}
