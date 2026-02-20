package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.util.ProAudio;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.playAudio;

public class Kuroka_Strike extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("Kuroka_Strike");
    // intellij stuff attack, enemy, basic, 6, 3,  , , , 

    public Kuroka_Strike() {
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = 6;
        tags.add(CardTags.STRIKE);
        tags.add(CardTags.STARTER_STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudio(ProAudio.KUROKASTRIKE);
        dmg(m, AbstractGameAction.AttackEffect.NONE);
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }
}