package reactkr.cards.mayo;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.powers.kuroka.MK_01_Majinai_Power;
import reactkr.powers.mayo.MM_03_EvasionPower;

import java.util.ArrayList;

import static reactkr.ModFile.makeID;

public class MM_36_Ricochet extends AbstractEasyCard_Mayo {
    public final static String ID = makeID("Ricochet");

    public MM_36_Ricochet() {
        super(ID, -2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseDamage = damage = 9;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void upp() {
        upgradeDamage(4);
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    @Override
    public void triggerOnManualDiscard() {
        ArrayList<AbstractMonster> aliveMonsters = new ArrayList<>();
        for (AbstractMonster mon : AbstractDungeon.getMonsters().monsters) {
            if (!mon.isDeadOrEscaped()) {
                aliveMonsters.add(mon);
            }
        }
        if (!aliveMonsters.isEmpty()) {
            int randomIndex = AbstractDungeon.miscRng.random(0, aliveMonsters.size() - 1);
            AbstractMonster randomMonster = aliveMonsters.get(randomIndex);

            if (randomMonster != null) {
                addToBot(new DamageAction(randomMonster, new DamageInfo(AbstractDungeon.player, damage)));
            }
        }
    }
}
