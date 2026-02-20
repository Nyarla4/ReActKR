package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.powers.kuroka.MK_01_Majinai_Power;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static reactkr.ModFile.makeID;

public class MK_25_MajinaiDraw extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("MajinaiDraw");
    // intellij stuff skill, self, uncommon, , , , , ,

    public MK_25_MajinaiDraw() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 2;
        baseSecondMagic = secondMagic = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(magicNumber));
        ArrayList<AbstractMonster> aliveMonsters = new ArrayList<>();
        for (AbstractMonster mon : AbstractDungeon.getMonsters().monsters) {
            if (!mon.isDeadOrEscaped()) {
                aliveMonsters.add(mon);
            }
        }
        if (!aliveMonsters.isEmpty()) {
            //System.out.println("DEBUG: 현재 살아있는 적의 수 = " + aliveMonsters.size());
            for (int i = 0; i < 2; i++) {
                Collections.shuffle(aliveMonsters, new Random(AbstractDungeon.cardRandomRng.randomLong()));
                AbstractMonster randomMonster = aliveMonsters.get(0);

                if (randomMonster != null) {
                    this.addToBot(new ApplyPowerAction(randomMonster, p, new MK_01_Majinai_Power(randomMonster, secondMagic), secondMagic));
                }
            }
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondMagic(2);
    }
}
