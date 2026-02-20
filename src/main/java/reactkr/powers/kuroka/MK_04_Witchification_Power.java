package reactkr.powers.kuroka;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import reactkr.powers.AbstractEasyPower;

import static reactkr.ModFile.makeID;

public class MK_04_Witchification_Power extends AbstractEasyPower {

    public static final String POWER_ID = makeID("WitchificationPower");

    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public MK_04_Witchification_Power(AbstractCreature owner) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, -1);

        //changeImg(WitchPath);
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + DESCRIPTIONS[1];
    }

    @Override
    public void onRemove() {
        super.onRemove();
    }

    @Override
    public void onVictory() {
        super.onVictory();
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
