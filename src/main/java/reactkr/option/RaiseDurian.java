package reactkr.option;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import reactkr.relics.DurianMonsterRelic;
import reactkr.vfx.MyCampfireRemoveEffect;

public class RaiseDurian extends AbstractCampfireOption {
    private DurianMonsterRelic relic;

    public RaiseDurian(boolean usable, DurianMonsterRelic relic){
        this.relic = relic;
        this.usable = usable;

        this.label = "두리안 키우기";
        this.description = usable ? "카드를 소모하여 두리안을 키웁니다." : "소모할 카드가 없습니다.";

        this.img = ImageMaster.CAMPFIRE_TRAIN_BUTTON;
    }

    @Override
    public void useOption() {
        if (this.usable) {
            AbstractDungeon.effectList.add(new MyCampfireRemoveEffect(this.relic));
        }
    }
}
