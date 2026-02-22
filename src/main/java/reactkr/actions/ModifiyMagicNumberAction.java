package reactkr.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import reactkr.cards.AbstractEasyCard;
import reactkr.cards.mayo.AbstractAimedCard;

import java.util.UUID;

public class ModifiyMagicNumberAction extends AbstractGameAction {
    private UUID uuid;
    private boolean isReset;

    public ModifiyMagicNumberAction(UUID targetUUID, int amount) {
        this(targetUUID, amount, false);
    }

    public ModifiyMagicNumberAction(UUID targetUUID, boolean isReset) {
        this(targetUUID, 0, isReset);
    }

    public ModifiyMagicNumberAction(UUID targetUUID, int amount, boolean isReset) {
        this.setValues(this.target, this.source, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.uuid = targetUUID;
        this.isReset = isReset;
    }

    public void update() {
        for (AbstractCard c : GetAllInBattleInstances.get(this.uuid)) {
            if (c instanceof AbstractEasyCard) {
                AbstractEasyCard ec = (AbstractEasyCard) c;
                if (isReset) {
                    if (ec instanceof AbstractAimedCard) {
                        AbstractAimedCard ac = (AbstractAimedCard) ec;
                        ac.baseMagicNumber = ac.basicDepletion();
                    }
                } else {
                    ec.baseMagicNumber += this.amount;
                    if (ec.baseMagicNumber < 0) {
                        ec.baseMagicNumber = 0;
                    }
                }
            }
        }

        this.isDone = true;
    }
}
