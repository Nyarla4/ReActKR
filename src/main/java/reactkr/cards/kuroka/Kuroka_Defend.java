package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.util.ProAudio;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.playAudio;

public class Kuroka_Defend extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("Kuroka_Defend");
    // intellij stuff skill, self, basic, , ,  5, 3, , 

    public Kuroka_Defend() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        baseBlock = 5;
        tags.add(CardTags.STARTER_DEFEND);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudio(ProAudio.KUROKADEFEND);
        blck();
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }
}