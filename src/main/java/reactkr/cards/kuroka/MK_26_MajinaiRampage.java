package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.powers.kuroka.MK_01_Majinai_Power;

import java.util.ArrayList;

import static reactkr.ModFile.makeID;

public class MK_26_MajinaiRampage extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("MajinaiRampage");
    // intellij stuff skill, self, uncommon, , , , , ,

    public MK_26_MajinaiRampage() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = 4;
        baseSecondMagic = secondMagic = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractMonster> aliveMonsters = new ArrayList<>();
        for (AbstractMonster mon : AbstractDungeon.getMonsters().monsters) {
            if (!mon.isDeadOrEscaped()) {
                aliveMonsters.add(mon);
            }
        }
        if (!aliveMonsters.isEmpty()) {
            //System.out.println("DEBUG: 현재 살아있는 적의 수 = " + aliveMonsters.size());
            for (int i = 0; i < secondMagic; i++) {
                int randomIndex = AbstractDungeon.miscRng.random(0, aliveMonsters.size() - 1);
                AbstractMonster randomMonster = aliveMonsters.get(randomIndex);

                if (randomMonster != null) {
                    this.addToBot(new ApplyPowerAction(randomMonster, p, new MK_01_Majinai_Power(randomMonster, magicNumber), magicNumber));
                }
            }
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }
}
