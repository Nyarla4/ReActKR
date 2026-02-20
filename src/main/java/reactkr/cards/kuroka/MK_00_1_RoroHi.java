package reactkr.cards.kuroka;

import com.evacipated.cardcrawl.mod.stslib.patches.core.AbstractCreature.TempHPField;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.Kuroka;
import reactkr.actions.AddRorokaHPAction;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.orbs.kuroka.MK_00_RorokaOrb;
import reactkr.util.ProAudio;

import static reactkr.ModFile.makeID;
import static reactkr.patches.RorokaTag.ROROKA;
import static reactkr.util.Wiz.playAudio;

public class MK_00_1_RoroHi extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("RoroHi");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public MK_00_1_RoroHi() {
        super(ID, 2, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        baseMagicNumber = magicNumber = 15;
        tags.add(ROROKA);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudio(ProAudio.ROROHI);
        //((Kuroka)p).RorokaMaxHp = Math.max(this.magicNumber, ((Kuroka)p).RorokaMaxHp);
        addToBot(new AddRorokaHPAction(p, p, this.magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(6);
    }
}