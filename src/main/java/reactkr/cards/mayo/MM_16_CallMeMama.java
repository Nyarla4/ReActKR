package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.Kuroka;
import reactkr.Latte;
import reactkr.actions.DiscoveryColorAction;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.configs.ModConfig;

import java.util.ArrayList;

import static reactkr.ModFile.makeID;

public class MM_16_CallMeMama extends AbstractEasyCard_Mayo {
    public final static String ID = makeID("CallMeMama");
    private final ArrayList<CardColor> colors = new ArrayList<>();

    public MM_16_CallMeMama() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 1;
        baseSecondMagic = secondMagic = 1;
        colors.add(Kuroka.Enums.KUROKA_COLOR);
        if(ModConfig.showLatte){
            colors.add(Latte.Enums.LATTE_COLOR);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ExhaustAction(this.magicNumber, false, false));
        for (int i = 0; i < this.secondMagic; i++) {
            this.addToBot(new DiscoveryColorAction(colors, 1));
        }
    }

    @Override
    public void upp() {
        upgradeSecondMagic(1);
    }
}
