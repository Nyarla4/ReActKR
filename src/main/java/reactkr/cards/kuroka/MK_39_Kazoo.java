package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.util.ProAudio;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.playAudio;

public class MK_39_Kazoo extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("Kazoo");
    // intellij stuff skill, self, common, , , , , 1, 2

    public MK_39_Kazoo() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 2;
        baseBlock = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudio(ProAudio.KUROKA_KAZOO);
        addToBot(new DrawCardAction(magicNumber));
        blck();
    }

    public void upp() {
        upgradeBlock(2);
    }

}
