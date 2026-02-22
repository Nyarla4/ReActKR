package reactkr.cards.subcards;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.util.ProAudio;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.applyToSelf;
import static reactkr.util.Wiz.playAudio;

public class MM_12_Five extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("Five");
    // intellij stuff skill, self, uncommon, , , , , ,

    public MM_12_Five() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE);
        baseMagicNumber = magicNumber = 5;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudio(ProAudio.YAMI);
        addToBot(new HealAction(p, p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }
}
