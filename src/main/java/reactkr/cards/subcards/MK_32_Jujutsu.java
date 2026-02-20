package reactkr.cards.subcards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.powers.kuroka.MK_01_Majinai_Power;

import static reactkr.ModFile.makeID;

public class MK_32_Jujutsu extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("Jujutsu");
    // intellij stuff skill, self, common, , , , , 1, 2

    public MK_32_Jujutsu() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
        baseMagicNumber = magicNumber = 6;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new MK_01_Majinai_Power(m, magicNumber)));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(6);
    }
}
