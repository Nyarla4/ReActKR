package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.powers.kuroka.MK_01_Majinai_Power;

import static reactkr.ModFile.makeID;

public class MK_24_MajinaiShield extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("MajinaiShield");
    // intellij stuff skill, self, uncommon, , , , , ,

    public MK_24_MajinaiShield() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseBlock = 3;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        int majinaiStack = 0;
        if(m.hasPower(MK_01_Majinai_Power.POWER_ID)){
            majinaiStack += m.getPower(MK_01_Majinai_Power.POWER_ID).amount;
        }
        if (majinaiStack > 0) {
            addToBot(new GainBlockAction(p, majinaiStack * magicNumber));
        }
    }

    @Override
    public void upp() {
        upgradeBaseCost(1);
    }
}
