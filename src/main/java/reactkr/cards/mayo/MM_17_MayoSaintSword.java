package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.Kuroka;
import reactkr.Latte;
import reactkr.actions.ExhaustAndGenerateAction;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.configs.ModConfig;

import java.util.ArrayList;

import static reactkr.ModFile.makeID;

public class MM_17_MayoSaintSword extends AbstractAimedCard {
    public final static String ID = makeID("MayoSaintSword");
    private final ArrayList<CardColor> colors = new ArrayList<>();

    public MM_17_MayoSaintSword() {
        super(ID, 3, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 15;
        baseSecondDamage = secondDamage = 20;
        baseMagicNumber = magicNumber = 10;
    }

    @Override
    public void normalUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, finalDamage)));
    }

    @Override
    public void aimedUse(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public int basicDepletion() {
        return 10;
    }

    @Override
    protected CardKind Kind() {
        return CardKind.ATK;
    }

    @Override
    public void upp() {
        upgradeSecondMagic(1);
    }
}
