package reactkr.cards.mayo;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.powers.mayo.MM_03_EvasionPower;

import java.util.ArrayList;

import static reactkr.ModFile.makeID;

public class MM_45_JETT extends AbstractUltCard {
    public final static String ID = makeID("JETT");

    public MM_45_JETT() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = block = 12;
    }

    @Override
    void normalUse(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    boolean ultUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                int curBlock = p.currentBlock;

                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new MM_03_EvasionPower(p, curBlock)));
                AbstractDungeon.actionManager.addToTop(new RemoveAllBlockAction(p, p));

                this.isDone = true;
            }
        });
        return true;
    }

    @Override
    public void upp() {

    }
}
