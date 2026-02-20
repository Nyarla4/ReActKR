package reactkr.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import reactkr.cards.AbstractEasyCard;

import java.util.UUID;

public class ModifiySecondDamageAction extends AbstractGameAction {
    private UUID uuid;

    public ModifiySecondDamageAction(UUID targetUUID, int amount) {
        this.setValues(this.target, this.source, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.uuid = targetUUID;
    }

    public void update() {
        for (AbstractCard c : GetAllInBattleInstances.get(this.uuid)) {
            if (c instanceof AbstractEasyCard) {
                AbstractEasyCard ec = (AbstractEasyCard) c;
                ec.baseSecondDamage += this.amount;
                if (ec.baseSecondDamage < 0) {
                    ec.baseSecondDamage = 0;
                }
            }
        }

        this.isDone = true;
    }
}
