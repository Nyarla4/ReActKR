package reactkr.powers.mayo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import reactkr.powers.AbstractEasyPower;

import java.util.ArrayList;

import static reactkr.ModFile.makeID;

public abstract class AbstractBulletPower extends AbstractEasyPower {

    // ── 4가지 발동 시나리오 ──────────────────────────────────
    public enum BulletTrigger {
        AIMED,    // 조준 상태일 때만
        QUICKED,  // 속사 상태일 때만
        OR,       // 조준 OR 속사일 때
        ALWAYS    // 항상 (조준 무관)
    }

    // ── 생성자 ───────────────────────────────────────────────
    public AbstractBulletPower(String ID, String name, AbstractCreature owner) {
        super(makeID(ID), name, PowerType.BUFF, false, owner, 1);
    }

    // ── 서브클래스 구현 인터페이스 ───────────────────────────
    public abstract void onFire();

    @Override
    public abstract void updateDescription();

    // ── 발사 ─────────────────────────────────────────────────
    private boolean fired = false;

    public final void fire() {
        if (fired) return;
        fired = true;
        onFire();
        AbstractDungeon.actionManager.addToBottom(
                new RemoveSpecificPowerAction(this.owner, this.owner, this.ID)
        );
    }

    // ── 정적 유틸리티 ────────────────────────────────────────

    /** 장전된 총알 반환. 없으면 null. */
    public static AbstractBulletPower getLoaded(AbstractCreature owner) {
        for (AbstractPower p : owner.powers) {
            if (p instanceof AbstractBulletPower) return (AbstractBulletPower) p;
        }
        return null;
    }

    /** 특정 클래스의 총알이 장전되어 있는지 확인. */
    public static boolean isLoaded(AbstractCreature owner,
                                   Class<? extends AbstractBulletPower> cls) {
        AbstractBulletPower b = getLoaded(owner);
        return b != null && cls.isInstance(b);
    }

    /**
     * 총알 장전. 기존 총알 전부 제거 후 교체.
     * 카드에서 ApplyPowerAction 직접 사용 금지 — 이 메소드로만 장전할 것.
     */
    public static void load(AbstractCreature owner, AbstractBulletPower newBullet) {
        for (AbstractPower p : new ArrayList<>(owner.powers)) {
            if (p instanceof AbstractBulletPower) {
                AbstractDungeon.actionManager.addToBottom(
                        new RemoveSpecificPowerAction(owner, owner, p.ID)
                );
            }
        }
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(owner, owner, newBullet, 1)
        );
    }

    // ── stackPower 봉인 ──────────────────────────────────────
    @Override
    public final void stackPower(int stackAmount) { }
}