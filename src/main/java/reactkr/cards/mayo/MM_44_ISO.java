package reactkr.cards.mayo;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Mayo;

import java.util.ArrayList;

import static reactkr.ModFile.makeID;

public class MM_44_ISO extends AbstractUltCard {
    public final static String ID = makeID("ISO");

    public MM_44_ISO() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 3;
        baseBlock = block = 5;
    }

    @Override
    void normalUse(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        blck();
    }

    @Override
    public boolean ultUse(AbstractPlayer p, AbstractMonster m) {
        boolean usable = false;
        for (AbstractMonster mon : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!mon.isDeadOrEscaped() && !mon.equals(m)) {
                usable = true;
                break;
            }
        }
        if (!usable) {
            return false;
        }

        ArrayList<AbstractMonster> targets = new ArrayList<>();
        for (AbstractMonster mon : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!mon.equals(m) && !mon.isDeadOrEscaped())
                targets.add(mon);
        }
        int randomIndex = AbstractDungeon.miscRng.random(0, targets.size() - 1);
        AbstractMonster randomMonster = targets.get(randomIndex);
        addToBot(new StunMonsterAction(randomMonster, p));
        return true;
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = ultCharged() && AbstractDungeon.getCurrRoom().monsters.monsters.size() >= 2 ?
                AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy() :
                AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
    }

    @Override
    public void upp() {

    }
}
