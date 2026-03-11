package reactkr.cards.mayo;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.util.ProAudio;

import java.util.ArrayList;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.playAudio;

public class MM_47_YouRat extends AbstractEasyCard_Mayo {
    public final static String ID = makeID("YouRat");

    public MM_47_YouRat() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 1;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudio(ProAudio.YOURAT);
        addToBot(new StunMonsterAction(m, p, magicNumber));
    }

    @Override
    public void upp() {
        updateCost(-1);
    }
}
