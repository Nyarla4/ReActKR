package reactkr.cards.kuroka;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.actions.AddRorokaHPAction;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.orbs.kuroka.MK_00_RorokaOrb;
import reactkr.powers.kuroka.MK_01_Majinai_Power;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.atb;

public class MK_33_NandeWatashi extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("NandeWatashi");
    // intellij stuff skill, self, common, , , , , 1, 2

    public MK_33_NandeWatashi() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AddRorokaHPAction(p, p, magicNumber));
        ArrayList<AbstractMonster> aliveMonsters = new ArrayList<>();
        for (AbstractMonster mon : AbstractDungeon.getMonsters().monsters) {
            if (!mon.isDeadOrEscaped() && mon.hasPower(MK_01_Majinai_Power.POWER_ID)) {
                aliveMonsters.add(mon);
            }
        }
        if (!aliveMonsters.isEmpty()) {
            addToBot(new AddRorokaHPAction(p, p, magicNumber));
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
