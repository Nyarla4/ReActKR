package reactkr.cards.kuroka;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.actions.AddRorokaHPAction;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.patches.RorokaTag;
import reactkr.vfx.RoroblastEffect;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.atb;

public class MK_19_RorokaBeam extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("RorokaBeam");
    // intellij stuff attack, enemy, uncommon, X, , , , ,
    private static final Logger logger = LogManager.getLogger(MK_19_RorokaBeam.class.getName());

    public MK_19_RorokaBeam() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = 8;
        isMultiDamage = true;
        baseMagicNumber = magicNumber = 6;
        tags.add(RorokaTag.ROROKA);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        // 2. 공격과 판정을 하나의 액션으로 통합
//        atb(new AbstractGameAction() {
//            @Override
//            public void update() {
//                // [중요] 대미지를 먼저 계산하여 입힙니다.
//                // multiDamage 배열을 사용하여 모든 적에게 피해를 줍니다.
//                for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
//                    AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
//                    if (!target.isDeadOrEscaped()) {
//                        // 직접 대미지를 주어 즉각적으로 체력을 깎습니다.
//                        target.damage(new DamageInfo(p, multiDamage[i], damageTypeForTurn));
//                    }
//                }
//
//                // [중요] 대미지 처리 직후 즉시 사망 여부 확인
//                boolean anyDied = false;
//                for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
//                    // currentHealth가 0 이하이거나 dying(죽어가는 중) 상태인지 확인
//                    if (mo.isDead || mo.currentHealth <= 0 || mo.isDying) {
//                        if (!mo.halfDead) {
//                            anyDied = true;
//                            break;
//                        }
//                    }
//                }
//
//                if (anyDied) {
//                    // addToTop을 사용해 현재 액션 직후에 회복이 일어나도록 합니다.
//                    this.addToTop(new HealAction(p, p, secondMagic));
//                }
//
//                // 대미지 폰트나 연출이 겹치지 않게 하기 위해 필요하다면 약간의 대기시간을 줄 수 있지만,
//                // 로직 확인을 위해선 여기서 종료합니다.
//                this.isDone = true;
//
//                // 만약 죽는 애니메이션을 기다려야 한다면 아래 코드를 추가할 수 있습니다.
//                if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
//                    AbstractDungeon.actionManager.clearPostCombatActions();
//                }
//            }
//        });
        this.addToBot(new VFXAction(p, new RoroblastEffect(p.dialogX, p.dialogY, p.flipHorizontal), 0.1F));
        // [흐름] 1. 전체 공격 액션을 먼저 수행
        this.addToBot(new DamageAllEnemiesAction(
                p,
                this.multiDamage,
                this.damageTypeForTurn,
                AbstractGameAction.AttackEffect.SLASH_HEAVY // [구조] 기본 이펙트 선택
        ));

        // [흐름] 2. 공격이 끝난 뒤(액션 큐의 다음 순서) 즉시 회복 수행
        this.addToBot(new AddRorokaHPAction(p, p, this.magicNumber));
    }

    public void upp() {
        upgradeDamage(3);
    }
}
