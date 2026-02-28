package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.powers.kuroka.MK_01_Majinai_Power;
import reactkr.powers.kuroka.MK_08_NeredKuroka_Power;
import reactkr.util.ProAudio;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.applyToSelf;
import static reactkr.util.Wiz.playAudio;

public class MK_35_DontNerKuroka extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("DontNerKuroka");
    // intellij stuff skill, self, common, , , , , 1, 2

    public MK_35_DontNerKuroka() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudio(ProAudio.DONTNER);
        applyToSelf(new MK_08_NeredKuroka_Power(p, 1));
    }

    public void upp() {
        this.isInnate = true;
    }
}
