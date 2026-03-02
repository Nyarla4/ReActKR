package reactkr.relics.kuroka;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerToRandomEnemyAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.Kuroka;
import reactkr.powers.kuroka.MK_01_Majinai_Power;
import reactkr.relics.AbstractEasyRelic;

import static reactkr.ModFile.makeID;

public class MK_06_MajitonomiconRelic extends AbstractEasyRelic {
    public static final String ID = makeID("MajitonomiconRelic");

    public MK_06_MajitonomiconRelic() {
        super(ID, RelicTier.UNCOMMON, LandingSound.FLAT, Kuroka.Enums.KUROKA_COLOR);
    }
    private static final Logger logger = LogManager.getLogger(MK_06_MajitonomiconRelic.class.getName());

    public void atTurnStart() {
        if(AbstractDungeon.player.currentBlock > 0){
            this.flash();
            this.addToBot(new ApplyPowerToRandomEnemyAction(AbstractDungeon.player, new MK_01_Majinai_Power(null, AbstractDungeon.player.currentBlock), AbstractDungeon.player.currentBlock, false, AbstractGameAction.AttackEffect.POISON));
        }
    }

    @Override
    public void initializeTips(){
        super.initializeTips();

        String keyword = makeID("주문");
        this.tips.add(new PowerTip(BaseMod.getKeywordProper(keyword),
                BaseMod.getKeywordDescription(keyword)));
    }
}
