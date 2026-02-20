package reactkr.potions;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.powers.AbstractPower;
import reactkr.ModFile;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.*;

public class NanikaSaintWater extends AbstractEasyPotion {
    public static String ID = makeID("NanikaSaintWater");

    public NanikaSaintWater() {
        super(ID, PotionRarity.COMMON,
                PotionSize.BOTTLE,
                new Color(0.2f, 0.4f, 0.9f, 1f),
                new Color(0.6f, 0.8f, 1.0f, 1f),
                null, null, ModFile.characterColor);
    }

    public int getPotency(int ascensionlevel) {
        return ascensionlevel;
    }

    public void use(AbstractCreature creature) {
        for (AbstractPower pow : adp().powers) {
            if (pow.type == AbstractPower.PowerType.DEBUFF) {
                addToBot(new RemoveSpecificPowerAction(adp(),adp(), pow));
            }
        }
    }

    public String getDescription() {
        return strings.DESCRIPTIONS[0];// + potency + strings.DESCRIPTIONS[1];
    }
}