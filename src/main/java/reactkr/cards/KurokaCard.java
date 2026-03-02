package reactkr.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.powers.ReactKRRangersPower;
import reactkr.powers.kuroka.MK_01_Majinai_Power;
import reactkr.util.ProAudio;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.playAudio;

public class KurokaCard extends AbstractEasyCard {
    public final static String ID = makeID("Kuroka");

    public KurokaCard() {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
        baseMagicNumber = magicNumber = 37;
        this.exhaust = true;
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (AbstractDungeon.player.hasPower(ReactKRRangersPower.POWER_ID)) {
            this.rawDescription = this.cardStrings.EXTENDED_DESCRIPTION[0];
        }
        this.initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudio(ProAudio.KUROKA);
        addToBot(new ApplyPowerAction(m, p, new MK_01_Majinai_Power(m, magicNumber)));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(10);
    }

    @Override
    public void triggerOnGlowCheck() {
        this.exhaust = !AbstractDungeon.player.hasPower(ReactKRRangersPower.POWER_ID);
    }
}
