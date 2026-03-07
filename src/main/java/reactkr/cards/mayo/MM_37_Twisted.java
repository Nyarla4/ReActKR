package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.powers.mayo.MM_03_EvasionPower;

import java.util.ArrayList;

import static reactkr.ModFile.makeID;

public class MM_37_Twisted extends AbstractEasyCard_Mayo {
    public final static String ID = makeID("Twisted");

    public MM_37_Twisted() {
        super(ID, -2, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 3;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

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
                AbstractPlayer p = AbstractDungeon.player;
                addToBot(new ApplyPowerAction(p, p, new MM_03_EvasionPower(p, magicNumber)));
            }
        }
    }
}
