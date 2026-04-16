package reactkr.relics.mayo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import reactkr.Mayo;
import reactkr.cards.subcards.MM_08_Deer01;
import reactkr.cards.subcards.MM_08_Rabbit01;
import reactkr.cards.subcards.MM_08_Wolf01;
import reactkr.powers.mayo.MM_03_EvasionPower;
import reactkr.powers.mayo.MM_12_ExcitedPower;
import reactkr.relics.AbstractEasyRelic;

import static reactkr.ModFile.makeID;

public class MM_08_DororongDarkRelic extends AbstractEasyRelic {
    public static final String ID = makeID("DororongDarkRelic");

    public MM_08_DororongDarkRelic() {
        super(ID, RelicTier.RARE, LandingSound.FLAT, Mayo.Enums.MAYO_COLOR);
    }

    @Override
    public void onEquip() {
        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(
                new MM_08_Rabbit01(),
                Settings.WIDTH / 2.0F,
                Settings.HEIGHT / 2.0F
        ));
        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(
                new MM_08_Deer01(),
                Settings.WIDTH / 2.0F,
                Settings.HEIGHT / 2.0F
        ));
        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(
                new MM_08_Wolf01(),
                Settings.WIDTH / 2.0F,
                Settings.HEIGHT / 2.0F
        ));
    }
}
