package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.util.ProAudio;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.playAudio;

public class MK_03_SuperDefend extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("SuperDefend");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public MK_03_SuperDefend() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock =block =8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudio(ProAudio.KUROKA_SUPERDEFEND);
        blck();
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }
}