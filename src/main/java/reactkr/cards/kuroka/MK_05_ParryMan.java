package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.ClashEffect;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.powers.GainArtifactPower;
import reactkr.util.MK_05_ParryManager;

import static reactkr.ModFile.makeID;

public class MK_05_ParryMan extends AbstractEasyCard_Kuroka {

    public final static String ID = makeID("ParryMan");
    public float parryDuration;
    // intellij stuff Skill, SELF, COMMON, 12, , , , ,

    public MK_05_ParryMan() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        baseDamage = 0;
        parryDuration = 0.75f;
        this.selfRetain = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (MK_05_ParryManager.isParryWindow) {
            // 성공 플래그 (중복 보장)
            MK_05_ParryManager.parrySuccess = true;

            // 적의 공격 수치 계산
            int parryDmg = m.getIntentDmg();

            int multiAmt = basemod.ReflectionHacks.getPrivate(m, AbstractMonster.class, "intentMultiAmt");
            int guardDmg = parryDmg * Math.max(multiAmt, 1);

            for (int i = 0; i < Math.max(multiAmt, 1); i++) {
                // [순서 주의: addToTop은 나중에 쓴 것이 먼저 실행됨]
                // 3. 마지막으로 데미지 입힘 (가장 먼저 실행됨)
                this.addToTop(new DamageAction(m,
                        new DamageInfo(p, parryDmg, this.damageTypeForTurn),
                        AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            }

            // 2. 이펙트 출력
            this.addToTop(new VFXAction(
                    new ClashEffect(m.hb.cX, m.hb.cY), 0.1F));

            AbstractPower artifactPower = m.getPower(ArtifactPower.POWER_ID);
            if (artifactPower != null) {

                int amount = artifactPower.amount;

                // [addToTop 실행 순서: 아래에서부터 위로 역순 실행됩니다]

                // (4) 가장 나중에 실행: 턴 종료 시 인공물 1개 복구 (힘 복구와 같은 시점)
                this.addToTop(new ApplyPowerAction(m, p, new GainArtifactPower(m, amount), amount));

                // (3) 힘 복구 예약: GainStrengthPower는 턴 종료 시 자동으로 힘을 돌려줍니다.
                this.addToTop(new ApplyPowerAction(m, p, new GainStrengthPower(m, guardDmg), guardDmg));

                // (2) 힘 감소: 인공물이 이 액션을 '막으면서' 인공물 1개가 사라집니다.
                this.addToTop(new ApplyPowerAction(m, p, new StrengthPower(m, -guardDmg), -guardDmg));

                this.addToTop(new RemoveSpecificPowerAction(m, p, artifactPower));
            } else {
                // 1. 적의 힘을 깎아 대미지를 0으로 만듦 (이게 가장 먼저 실행되어야 함)
                this.addToTop(new ApplyPowerAction(m, p,
                        new StrengthPower(m, -guardDmg), -guardDmg));

                // 이번 턴 끝나고 힘 복구
                this.addToTop(new ApplyPowerAction(m, p,
                        new GainStrengthPower(m, guardDmg), guardDmg));
            }
        }
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (MK_05_ParryManager.isParryWindow) {
            this.freeToPlayOnce = true;
            this.setCostForTurn(0);
            return true;
        }
        return super.canUse(p, m);
    }

    @Override
    public void upp() {
        if (!upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.parryDuration = 1.5f;
            initializeDescription();
        }
    }
}
