package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.powers.DelayedPower;
import reactkr.powers.mayo.MM_03_EvasionPower;

import static reactkr.ModFile.makeID;

public class MM_40_ChicagoTypewriter extends AbstractEasyCard_Mayo {
    public final static String ID = makeID("ChicagoTypewriter");

    public MM_40_ChicagoTypewriter() {
        super(ID, -1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 10;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int damageX = EnergyPanel.totalCount;

        if (this.energyOnUse != -1) {
            damageX = this.energyOnUse;
        }
        if (p.hasRelic("Chemical X")) {
            damageX += 2;
        }

        if (!this.freeToPlayOnce) {
            p.energy.use(EnergyPanel.totalCount);
        }

        addToBot(new ApplyPowerAction(m, p, new MM_03_EvasionPower(m, magicNumber)));
        addToBot(new ApplyPowerAction(m, p, new DelayedPower(m, 1, new MM_03_EvasionPower(m, -magicNumber), true)));
        int finalDamageX = damageX;
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
            for (int i = 0; i < magicNumber; i++) {
                this.addToTop(new DamageAction(m, new DamageInfo(p, finalDamageX, DamageInfo.DamageType.NORMAL), AttackEffect.BLUNT_LIGHT));
            }
            this.isDone = true;
            }
        });
    }

    @Override
    public void upp() {
        upgradeMagicNumber(5);
    }
}
