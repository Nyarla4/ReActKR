package reactkr.cards.mayo;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
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
        super(ID, 3, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = damage = 25;
        baseSecondDamage = secondDamage = 30;
        baseMagicNumber = magicNumber = 15;
        baseSecondMagic = secondMagic = 1;
        useAim = true;
        useQuick = true;
    }

    @Override
    public void normalUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, finalDamage)));
    }

    @Override
    public void aimedUse(AbstractPlayer p, AbstractMonster m){
        addToBot(new DamageAllEnemiesAction(
                p,
                magicNumber,
                this.damageTypeForTurn,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY
        ));
    }

    @Override
    public void quickUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new StunMonsterAction(m, p, secondMagic));
    }

    @Override
    public void upp() {

    }
}
