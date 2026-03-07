package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.powers.mayo.MM_10_AimPower;

import java.util.ArrayList;

import static reactkr.ModFile.makeID;

public class MM_42_tempShot extends AbstractAimedCard {
    public final static String ID = makeID("tempShot");

    public MM_42_tempShot() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL);
        baseDamage = damage = 8;
        baseSecondDamage = secondDamage = 12;
        useAim = true;
    }

    @Override
    public void normalUse(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCreature> targets = new ArrayList<>(AbstractDungeon.getCurrRoom().monsters.monsters);
        if (!isAimed()) {
            targets.add(p);
        }
        int randomIndex = AbstractDungeon.miscRng.random(0, targets.size() - 1);
        AbstractCreature randomTarget = targets.get(randomIndex);
        addToBot(new DamageAction(randomTarget, new DamageInfo(p, finalDamage)));
    }

    @Override
    protected boolean useBullet() {
        return true;
    }

    @Override
    public int basicDepletion() {
        return -1;
    }

    @Override
    public void upp() {
        updateCost(-1);
    }
}
