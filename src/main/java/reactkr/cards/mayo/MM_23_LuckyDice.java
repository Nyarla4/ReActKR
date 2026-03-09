package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.GainGoldTextEffect;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
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
        if (isAimed()) {
            damage = 7;
        } else {
            damage = (roll <= secondMagic) ? 7 : AbstractDungeon.cardRandomRng.random(1, 6);
        }
        results[0] = damage;

        roll = AbstractDungeon.cardRandomRng.random(1, 100);
        block = (roll <= secondMagic) ? 7 : AbstractDungeon.cardRandomRng.random(1, 6);
        results[1] = block;

        roll = AbstractDungeon.cardRandomRng.random(1, 100);
        magicNumber = (roll <= secondMagic) ? 7 : AbstractDungeon.cardRandomRng.random(1, 6);
        results[2] = magicNumber;

        // 슬롯머신 효과
        this.addToBot(new TripleSlotAction(results));

        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
        addToBot(new GainBlockAction(p, block));
        addToBot(new ApplyPowerAction(p, p, new MM_03_EvasionPower(p, magicNumber)));

        int goldAmount = damage * block * magicNumber;
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                AbstractDungeon.player.gainGold(goldAmount);
                AbstractDungeon.effectList.add(new RainingGoldEffect(goldAmount));
                AbstractDungeon.effectList.add(new GainGoldTextEffect(goldAmount));
                this.isDone = true;
            }
        });
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