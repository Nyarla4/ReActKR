package reactkr.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import reactkr.cards.mayo.AbstractAimedCard;
import reactkr.powers.ReactKRRangersPower;
import reactkr.util.ProAudio;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.playAudio;

public class MayoCard extends AbstractAimedCard {
    public final static String ID = makeID("Mayo");

    public MayoCard() {
        super(ID, 1, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
        baseDamage = damage = 11;
        baseSecondDamage = secondDamage = 13;
        baseMagicNumber = magicNumber = 5;
        baseSecondMagic = secondMagic = 7;
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (AbstractDungeon.player.hasPower(ReactKRRangersPower.POWER_ID)) {
            this.rawDescription = this.cardStrings.EXTENDED_DESCRIPTION[0];
        }
        this.initializeDescription();
    }

    @Override
    public int basicDepletion() {
        return -1;
    }

    @Override
    protected CardKind Kind() {
        return CardKind.ATK;
    }

    @Override
    public void normalUse(AbstractPlayer p, AbstractMonster m) {
        playAudio(ProAudio.MAYO);
        addToBot(new DamageAction(m, new DamageInfo(p, finalDamage)));
    }

    @Override
    public  void aimedUse(AbstractPlayer p, AbstractMonster m) {
        if (p.hasPower(ReactKRRangersPower.POWER_ID)) {
            addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, secondMagic, false)));
        } else {
            addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, magicNumber, false)));
        }
    }

    @Override
    public void upp() {
        //updateCost(-1);
        upgradeDamage(6);
        upgradeSecondDamage(6);
    }
}
