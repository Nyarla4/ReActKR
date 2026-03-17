package reactkr.cards.subcards;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.util.ProAudio;

import java.util.ArrayList;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.*;

public class MM_12_Seven extends AbstractEasyCard_Mayo {
    public final static String ID = makeID("Seven");
    // intellij stuff skill, self, uncommon, , , , , ,

    public MM_12_Seven() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE);
        baseMagicNumber = magicNumber = 7;
        baseSecondMagic = secondMagic = 7;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudio(ProAudio.SASI);
        if(!upgraded){
            addToBot(new DamageAction(p, new DamageInfo(p, magicNumber)));
        }
        applyToSelf(new StrengthPower(p, secondMagic));
    }

    @Override
    public void upp() {

    }
}
