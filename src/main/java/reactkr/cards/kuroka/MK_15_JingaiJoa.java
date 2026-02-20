package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.powers.kuroka.MK_03_DelusionFactor_Power;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.applyToSelf;

public class MK_15_JingaiJoa extends AbstractWitchificateCard {
    public final static String ID = makeID("JingaiJoa");
    // intellij stuff skill, self, common, , , , , 1, 2

    public MK_15_JingaiJoa() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 8;
        baseMagicNumber = magicNumber = 2;
        initializeWitchPreview();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        if (witchificated) {
            dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        }
        if (!witchificated) {
            applyToSelf(new MK_03_DelusionFactor_Power(p, magicNumber));
        }
    }

    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(1);
    }

    @Override
    void Witchification() {
        if (witchificated) {
            this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
        } else {
            this.rawDescription = cardStrings.DESCRIPTION;
        }
    }
}
