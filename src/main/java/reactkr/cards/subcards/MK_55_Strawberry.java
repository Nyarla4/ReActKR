package reactkr.cards.subcards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.util.ProAudio;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.playAudio;

public class MK_55_Strawberry extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("Strawberry");

    public MK_55_Strawberry() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE, CardColor.COLORLESS);
        baseBlock = block = 10;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudio(ProAudio.STRAWBERRY);
        blck();
    }

    @Override
    public void upp() {
        upgradeBlock(2);
    }
}
