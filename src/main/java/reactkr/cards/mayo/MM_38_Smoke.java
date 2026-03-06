package reactkr.cards.mayo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.powers.DelayedPower;
import reactkr.powers.mayo.MM_03_EvasionPower;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static reactkr.ModFile.makeID;

public class MM_38_Smoke extends AbstractEasyCard_Mayo {
    public final static String ID = makeID("Smoke");

    public MM_38_Smoke() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 30;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new MM_03_EvasionPower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new DelayedPower(p, 1, new MM_03_EvasionPower(p, -magicNumber), true)));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(20);
    }
}
