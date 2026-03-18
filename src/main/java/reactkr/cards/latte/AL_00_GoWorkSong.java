package reactkr.cards.latte;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Latte;

import static reactkr.ModFile.makeID;

public class AL_00_GoWorkSong extends AbstractMorningCard {
    public final static String ID = makeID("GoWorkSong");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public AL_00_GoWorkSong() {
        super(ID, 3, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = 12;
        baseMagicNumber = magicNumber = 2;
        initializeSummaryPreview();
    }

    @Override
    public void morningUse(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        if (isFirst) {
            this.addToBot(new GainEnergyAction(magicNumber));
        }
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }

    @Override
    protected int getPreviewDescriptionIndex() {
        return 0;
    }

    @Override
    protected int getPreviewUpgradeDescriptionIndex() {
        return -1;
    }
}