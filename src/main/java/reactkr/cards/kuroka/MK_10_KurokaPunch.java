package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.powers.kuroka.MK_01_Majinai_Power;
import reactkr.util.ProAudio;


import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.playAudio;

public class MK_10_KurokaPunch extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("KurokaPunch");
    // intellij stuff attack, enemy, uncommon, X, , , , ,

    public MK_10_KurokaPunch() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudio(ProAudio.KURO_PUNCH);
        int repeatAmount = 1;
        if (m.hasPower(MK_01_Majinai_Power.POWER_ID)) {
            repeatAmount += m.getPower(MK_01_Majinai_Power.POWER_ID).amount;
        }
        repeatAmount = Math.min(repeatAmount, 6);

        int finalRepeatAmount = repeatAmount;
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
            for (int i = 0; i < finalRepeatAmount; i++) {
                // addToTop을 사용해 다른 액션보다 먼저 차례대로 실행되게 함
                this.addToTop(new DamageAction(m, new DamageInfo(p, damage, damageType), AttackEffect.BLUNT_LIGHT));
            }
            this.isDone = true;
            }
        });
    }

    public void upp() {
        upgradeDamage(2);
    }
}
