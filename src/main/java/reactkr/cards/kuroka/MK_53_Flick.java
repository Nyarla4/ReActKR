package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import reactkr.powers.kuroka.MK_03_DelusionFactor_Power;
import reactkr.util.ProAudio;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.playAudio;

public class MK_53_Flick extends AbstractWitchificateCard {
    public final static String ID = makeID("Flick");
    // intellij stuff skill, self, common, , , , , 1, 2

    public MK_53_Flick() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 10;
        baseSecondDamage = secondDamage = 15;
        baseMagicNumber = magicNumber = 3;
        initializeWitchPreview();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudio(ProAudio.FLICK);
        int dmg = witchificated ? secondDamage : damage;
        if (p.hasPower(MK_03_DelusionFactor_Power.POWER_ID)) {
            AbstractPower pow = p.getPower(MK_03_DelusionFactor_Power.POWER_ID);
            if (pow.amount >= magicNumber) {
                addToBot(new ApplyPowerAction(p, p, new MK_03_DelusionFactor_Power(p, -magicNumber)));
                dmg *= 2;
            }
        }
        addToBot(new DamageAction(m, new DamageInfo(p, dmg)));
    }

    public void upp() {
        upgradeDamage(5);
        upgradeSecondDamage(5);
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
