package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.powers.DelayedPower;
import reactkr.util.ProAudio;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.playAudio;

public class MM_01_PiercingLaugh extends AbstractEasyCard_Mayo {
    public final static String ID = makeID("PiercingLaugh");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public MM_01_PiercingLaugh() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = 7;
        baseSecondMagic = secondMagic = 1;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (AbstractDungeon.cardRandomRng.randomBoolean(0.5f)) {
            playAudio(ProAudio.PIERCINGLAUGH);
        } else {
            playAudio(ProAudio.MAYO_KKINGKKING);
        }
        this.addToBot(new SFXAction("ATTACK_PIERCING_WAIL"));
        if (Settings.FAST_MODE) {
            this.addToBot(new VFXAction(p, new ShockWaveEffect(p.hb.cX, p.hb.cY, Settings.GREEN_TEXT_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), 0.3F));
        } else {
            this.addToBot(new VFXAction(p, new ShockWaveEffect(p.hb.cX, p.hb.cY, Settings.GREEN_TEXT_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), 1.5F));
        }

        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            this.addToBot(new ApplyPowerAction(mo, p, new StrengthPower(mo, -this.magicNumber), -this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        }

        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!mo.hasPower("Artifact")) {
                //this.addToBot(new ApplyPowerAction(mo, p, new GainStrengthPower(mo, this.magicNumber), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
                this.addToBot(new ApplyPowerAction(mo, p, new DelayedPower(mo, this.secondMagic, new StrengthPower(mo, this.magicNumber))));
            }
        }
    }

    @Override
    public void upp() {
        upgradeSecondMagic(1);
    }

}