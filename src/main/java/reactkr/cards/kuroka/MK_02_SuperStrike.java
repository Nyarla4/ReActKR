package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.powers.kuroka.MK_01_Majinai_Power;
import reactkr.util.ProAudio;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.playAudio;

public class MK_02_SuperStrike extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("SuperStrike");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public MK_02_SuperStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 12;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudio(ProAudio.KUROKA_SUPERSTRIKE);
        dmg(m, AbstractGameAction.AttackEffect.NONE);
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }
}