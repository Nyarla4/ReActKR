package reactkr.stances.mayo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.stances.AbstractStance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.ModFile;
import reactkr.powers.kuroka.MK_01_Majinai_Power;
import reactkr.powers.mayo.MM_01_OWUltPower;

public class OverwatchStance extends AbstractStance {
    public static final String STANCE_ID = ModFile.makeID("Overwatch");
    private static final StanceStrings stanceStrings;
    private static final String NAME;
    private static final String[] DESCRIPTION;

    private static final Logger logger = LogManager.getLogger(OverwatchStance.class.getName());

    public OverwatchStance() {
        this.ID = STANCE_ID;
        this.name = NAME;
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = stanceStrings.DESCRIPTION[0];
    }

    public void onEnterStance() {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new MM_01_OWUltPower(p, 1), 1)
        );
    }

    public void onExitStance() {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToBottom(
                new RemoveSpecificPowerAction(p, p, MM_01_OWUltPower.POWER_ID)
        );
    }

    static {
        stanceStrings = CardCrawlGame.languagePack.getStanceString(STANCE_ID);
        NAME = stanceStrings.NAME;
        DESCRIPTION = stanceStrings.DESCRIPTION;
    }
}
