package reactkr.relics.kuroka;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.Kuroka;
import reactkr.powers.kuroka.MK_01_Majinai_Power;
import reactkr.powers.kuroka.MK_03_DelusionFactor_Power;
import reactkr.relics.AbstractEasyRelic;

import static reactkr.ModFile.makeID;

public class MK_02_BibRelic extends AbstractEasyRelic {
    public static final String ID = makeID("BibRelic");

    public MK_02_BibRelic() {
        super(ID, RelicTier.COMMON, LandingSound.FLAT, Kuroka.Enums.KUROKA_COLOR);
    }

    public void onEquip() {
        ((Kuroka) AbstractDungeon.player).RorokaMaxHp += 15;
    }

    @Override
    public void initializeTips(){
        super.initializeTips();

        String keyword = makeID("로로카");
        this.tips.add(new PowerTip(BaseMod.getKeywordProper(keyword),
                BaseMod.getKeywordDescription(keyword)));
    }
}
