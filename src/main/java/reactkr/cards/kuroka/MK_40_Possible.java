package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlyingOrbEffect;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.powers.kuroka.MK_03_DelusionFactor_Power;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.applyToSelf;
import static reactkr.util.Wiz.atb;

public class MK_40_Possible extends AbstractWitchificateCard {
    public final static String ID = makeID("Possible");
    // intellij stuff skill, self, common, , , , , 1, 2

    public MK_40_Possible() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 3;
        baseMagicNumber = magicNumber = 4;
        baseSecondMagic = secondMagic = 6;
        initializeWitchPreview();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = (witchificated ? secondMagic : magicNumber);

        for (int i = 0; i < count; i++) {
            // 1. 공격 액션 예약 (이펙트 포함)
            // AttackEffect.BLUNT_LIGHT 등을 넣으면 dmg()와 동일한 연출이 나옵니다.
            atb(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
                    AbstractGameAction.AttackEffect.BLUNT_LIGHT));

            // 2. 해당 공격 직후에 실행될 판정 및 파워 부여 액션 예약
            if (!witchificated) {
                atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        // 바로 위 DamageAction이 실행된 직후의 m.lastDamageTaken을 체크
                        if (m.lastDamageTaken > 0) {
                            // 순서를 확실히 하기 위해 addToTop 사용
                            this.addToTop(new ApplyPowerAction(p, p,
                                    new MK_03_DelusionFactor_Power(p, 1), 1));
                        }
                        this.isDone = true;
                    }
                });
            }
        }
    }

    public void upp() {
        upgradeDamage(1);
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
