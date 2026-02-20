package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.orbs.mayo.MM_01_SniperBulletOrb;
import reactkr.orbs.mayo.MM_02_LightBulletOrb;

import static reactkr.ModFile.makeID;

public class MM_000_GetLight extends AbstractEasyCard_Mayo {
    public final static String ID = makeID("GetLight");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public MM_000_GetLight() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
    }

    @Override
    public void upp() {
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToTop(new ChannelAction(new MM_02_LightBulletOrb(magicNumber)));
    }
}