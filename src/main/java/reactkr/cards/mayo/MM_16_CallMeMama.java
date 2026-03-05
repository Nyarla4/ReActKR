package reactkr.cards.mayo;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import reactkr.Kuroka;
import reactkr.Latte;
import reactkr.actions.ExhaustAndGenerateAction;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.configs.ModConfig;

import java.util.ArrayList;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.applyToSelf;

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
        addToBot(new ExhaustAndGenerateAction(colors, magicNumber, secondMagic));
    }

    @Override
    public void upp() {
        upgradeSecondMagic(1);
    }
}
