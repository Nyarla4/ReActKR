package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NoDrawPower;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.util.ProAudio;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.playAudio;

public class MK_36_DaisukiChu extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("DaisukiChu");
    // intellij stuff skill, self, common, , , , , 1, 2

    public MK_36_DaisukiChu() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudio(ProAudio.DAISUKICHU);
        addToBot(new ApplyPowerAction(p,p,new NoDrawPower(p)));
        for (AbstractCard hand : p.hand.group) {
            hand.setCostForTurn(0);
        }
    }

    public void upp() {
        upgradeBaseCost(2);
    }

}
