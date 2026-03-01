package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.powers.kuroka.MK_01_Majinai_Power;
import reactkr.powers.kuroka.MK_03_DelusionFactor_Power;
import reactkr.util.ProAudio;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.playAudio;

public class MK_14_YuriAtkDef extends AbstractWitchificateCard {
    public final static String ID = makeID("YuriAtkDef");
    // intellij stuff skill, self, common, , , , , 1, 2

    private static final Logger logger = LogManager.getLogger(MK_14_YuriAtkDef.class.getName());

    public MK_14_YuriAtkDef() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        this.exhaust = true;
        initializeWitchPreview();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudio(ProAudio.YURIATKDEF);
        //this.addToBot(new RemoveAllPowersAction(p, true));
        if (!witchificated) {
            addToBot(new ApplyPowerAction(p, p, new MK_03_DelusionFactor_Power(p, 3), 3));
            int atk = 0;
            int def = 0;
            String strId = "";
            String dexId = "";
            if (p.hasPower(StrengthPower.POWER_ID)) {
                AbstractPower curStr = p.getPower(StrengthPower.POWER_ID);
                atk = curStr.amount;
                strId = curStr.ID;
            }
            if (p.hasPower(DexterityPower.POWER_ID)) {
                AbstractPower curDex = p.getPower(DexterityPower.POWER_ID);
                def = curDex.amount;
                dexId = curDex.ID;
            }

            if (!strId.isEmpty()) {
                addToBot(new RemoveSpecificPowerAction(p, p, strId));
            }
            if (!dexId.isEmpty()) {
                addToBot(new RemoveSpecificPowerAction(p, p, dexId));
            }

            if (def != 0) {
                addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, def), def));
            }
            if (atk != 0) {
                addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, atk), atk));
            }
        } else {//마녀화 효과
            //int amount = p.getPower(MK_03_DelusionFactor_Power.POWER_ID).amount / magicNumber;
            addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));
            addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, 1), 1));

            int atk = 0;
            int def = 0;
            if (p.hasPower(StrengthPower.POWER_ID)) {
                atk = p.getPower(StrengthPower.POWER_ID).amount;
            }
            if (p.hasPower(DexterityPower.POWER_ID)) {
                def = p.getPower(DexterityPower.POWER_ID).amount;
            }
            if (atk > def) {
                addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, atk - def), atk - def));
            } else if (def > atk) {
                addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, def - atk), def - atk));
            }
        }
    }

    public void upp() {
        upgradeBaseCost(0);
    }

    @Override
    void Witchification() {
        if (witchificated) {
            //this.exhaust = false;
            this.rawDescription = this.cardStrings.EXTENDED_DESCRIPTION[0];
        } else {
            //this.exhaust = true;
            this.rawDescription = this.cardStrings.DESCRIPTION;
        }
    }
}
