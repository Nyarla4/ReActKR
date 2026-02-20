package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.powers.kuroka.MK_03_DelusionFactor_Power;

import static reactkr.ModFile.makeID;

public class MK_16_SixArms extends AbstractWitchificateCard {
    public final static String ID = makeID("SixArms");
    private int del;
    // intellij stuff skill, self, common, , , , , 1, 2

    public MK_16_SixArms() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 6;
        del = 10;
        this.exhaust = true;
        initializeWitchPreview();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, witchificated ? del : damage, DamageInfo.DamageType.NORMAL)));
        this.addToBot(new BetterDiscardPileToHandAction(1));
    }

    public void upp() {
        this.exhaust = false;
    }

    @Override
    void Witchification() {
        AbstractPlayer p = AbstractDungeon.player;
        if (p != null) {
            AbstractPower pow = p.getPower(MK_03_DelusionFactor_Power.POWER_ID);
            del = pow == null ? 10 : pow.amount;
        }
        if (witchificated) {
            this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0] + del + (upgraded ? cardStrings.EXTENDED_DESCRIPTION[2] : cardStrings.EXTENDED_DESCRIPTION[1]);
        } else {
            this.rawDescription = upgraded ? cardStrings.UPGRADE_DESCRIPTION : cardStrings.DESCRIPTION;
        }
    }
}
