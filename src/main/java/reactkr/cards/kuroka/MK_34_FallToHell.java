package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.powers.kuroka.MK_01_Majinai_Power;

import static reactkr.ModFile.makeID;

public class MK_34_FallToHell extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("FallToHell");
    // intellij stuff skill, self, common, , , , , 1, 2

    public MK_34_FallToHell() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int majinai = m.getPower(MK_01_Majinai_Power.POWER_ID).amount * (upgraded ? 2 : 1);
        this.addToBot(new ApplyPowerAction(m, p,
                new MK_01_Majinai_Power(m,majinai), majinai));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canuse = super.canUse(p, m);
        if (!canuse) return false;

        if (m != null) {
            if (!m.hasPower(MK_01_Majinai_Power.POWER_ID)) {
                this.cantUseMessage = "주문에 걸려있지 않아...";
                return false;
            }
        }

        return true;
    }
}
