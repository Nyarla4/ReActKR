package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import reactkr.actions.TripleSlotAction;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.powers.mayo.MM_03_EvasionPower;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.applyToSelf;

public class MM_23_LuckyDice extends AbstractAimedCard {
    public final static String ID = makeID("LuckyDice");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public MM_23_LuckyDice() {
        super(ID, 0, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 1;
        baseSecondDamage = secondDamage = 6;
        baseSecondMagic = secondMagic = 5;
        this.exhaust = true;
    }

    @Override
    public void normalUse(AbstractPlayer p, AbstractMonster m) {
        int[] results = new int[3];

        // 롤 결과
        int roll = AbstractDungeon.cardRandomRng.random(1, 100);
        if(isAimed()){
            baseDamage = 7;
        }
        else{
            baseDamage = (roll <= secondMagic) ? 7 : AbstractDungeon.cardRandomRng.random(1, 6);
        }
        results[0] = baseDamage;

        roll = AbstractDungeon.cardRandomRng.random(1, 100);
        baseBlock = (roll <= secondMagic) ? 7 : AbstractDungeon.cardRandomRng.random(1, 6);
        results[1] = baseBlock;

        roll = AbstractDungeon.cardRandomRng.random(1, 100);
        baseMagicNumber = (roll <= secondMagic) ? 7 : AbstractDungeon.cardRandomRng.random(1, 6);
        results[2] = baseMagicNumber;

        // 슬롯머신 효과
        this.addToBot(new TripleSlotAction(results));

        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        addToBot(new GainBlockAction(p, baseBlock));
        applyToSelf(new MM_03_EvasionPower(p, baseMagicNumber));

        p.gainGold(baseDamage * baseBlock * baseMagicNumber);
    }

    @Override
    public void upp() {
        upgradeSecondMagic(2);
    }

    @Override
    protected boolean useBullet() {
        return false;
    }

    @Override
    public int basicDepletion() {
        return -1;
    }
}