package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.powers.kuroka.MK_01_Majinai_Power;
import reactkr.util.ProAudio;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.playAudio;

public class MK_29_NoData extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("NoData");
    // intellij stuff skill, self, uncommon, , , , , ,

    public MK_29_NoData() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseBlock = block = 5;
        baseMagicNumber = magicNumber = 7;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudio(ProAudio.NODATA);
        blck();
        this.addToBot(new ApplyPowerAction(m, p,
                new MK_01_Majinai_Power(m, magicNumber), magicNumber));
    }

    @Override
    public void upp() {
        upgradeBlock(3);
        upgradeMagicNumber(3);
    }
}
