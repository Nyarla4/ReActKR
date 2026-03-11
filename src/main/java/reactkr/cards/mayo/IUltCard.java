package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.powers.mayo.MM_01_UltPower;

public interface IUltCard {

    // 1. 카드가 구현해야 할 궁극기 발동 내용
    boolean ultUse(AbstractPlayer p, AbstractMonster m);

    // 2. 궁극기 게이지 충전 확인 로직
    default boolean ultCharged() {
        AbstractPlayer p = AbstractDungeon.player;
        if (!p.hasPower(MM_01_UltPower.POWER_ID)) {
            return false;
        }
        return p.getPower(MM_01_UltPower.POWER_ID).amount >= MM_01_UltPower.maxAmount;
    }

    // 3. 궁극기 처리
    default void handleUltFlow(AbstractPlayer p, AbstractMonster m) {
        if(!p.hasPower(MM_01_UltPower.POWER_ID)){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new MM_01_UltPower(p, 1), 1));
        }

        if (ultCharged()) {
            if(ultUse(p, m)) {
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, MM_01_UltPower.POWER_ID));
            }
        }
    }
}