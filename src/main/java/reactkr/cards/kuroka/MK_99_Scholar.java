package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import reactkr.cards.AbstractEasyCard_Kuroka;

import static reactkr.ModFile.*;

public class MK_99_Scholar extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("Scholar");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public MK_99_Scholar() {
        super(ID, -2, CardType.CURSE, CardRarity.CURSE, CardTarget.NONE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.dontTriggerOnUseCard) {
            this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VulnerablePower(AbstractDungeon.player, 1, true), 1));
        }
    }

    public void upp() {

    }
}