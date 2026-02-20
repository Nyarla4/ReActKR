package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.powers.kuroka.MK_01_Majinai_Power;
import reactkr.util.ProAudio;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.playAudio;

public class MK_23_MajinaiChop extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("MajinaiChop");
    // intellij stuff skill, self, uncommon, , , , , ,

    public MK_23_MajinaiChop() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 4;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudio(ProAudio.KUROKA_CHOP);
        if(m.hasPower(MK_01_Majinai_Power.POWER_ID)){
            damage *= magicNumber;
        }
        addToBot(new DamageAction(m,new DamageInfo(p,damage,damageTypeForTurn)));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}
