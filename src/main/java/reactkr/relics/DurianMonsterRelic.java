package reactkr.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import reactkr.option.RaiseDurian;

import java.util.ArrayList;

import static reactkr.ModFile.makeID;

public class DurianMonsterRelic extends AbstractEasyRelic {
    public static final String ID = makeID("DurianMonsterRelic");

    public DurianMonsterRelic() {
        super(ID, RelicTier.SPECIAL, LandingSound.FLAT, null);
        this.counter = 1;
    }

    @Override
    public String getUpdatedDescription() {
        int displayCounter = (this.counter == -1) ? 1 : this.counter;
        return displayCounter + DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStart() {
        if (this.counter > 0) {
            this.flash(); // 유물 번쩍임 이펙트
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
                    AbstractDungeon.player, AbstractDungeon.player,
                    new StrengthPower(AbstractDungeon.player, this.counter), this.counter
            ));
        }
    }

    @Override
    public void addCampfireOption(ArrayList<AbstractCampfireOption> options) {
        boolean canUse = !AbstractDungeon.player.masterDeck.getPurgeableCards().isEmpty();
        options.add(new RaiseDurian(canUse, this)); // 휴식처 UI 리스트에 내 전용 버튼 구조를 밀어 넣음
    }

    public void incrementCounter() {
        this.counter++;
        this.description = this.getUpdatedDescription();
    }

    @Override
    public void setCounter(int setCounter) {
        super.setCounter(setCounter);

        if (AbstractDungeon.player != null) {
            this.description = this.getUpdatedDescription();
            this.tips.clear();
            this.tips.add(new com.megacrit.cardcrawl.helpers.PowerTip(this.name, this.description));
            this.initializeTips();
        }
    }

    public AbstractRelic makeCopy() {
        return new DurianMonsterRelic();
    }

    @Override
    public boolean canSpawn() {
        return false;
    }
}
