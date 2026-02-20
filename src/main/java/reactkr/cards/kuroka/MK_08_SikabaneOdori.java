package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.actions.ModifiySecondDamageAction;
import reactkr.cards.AbstractEasyCard_Kuroka;

import static reactkr.ModFile.makeID;

public class MK_08_SikabaneOdori extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("SikabaneOdori");
    // intellij stuff attack, all enemy, common, 4, , , , ,

    public MK_08_SikabaneOdori() {
        super(ID, 1, AbstractCard.CardType.ATTACK, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ALL_ENEMY);
        baseDamage = damage = 4;
        baseSecondDamage = secondDamage = 12;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int randomValue = AbstractDungeon.cardRandomRng.random(damage, secondDamage);
        this.addToBot(new DamageAllEnemiesAction(p,
                DamageInfo.createDamageMatrix(randomValue, true),
                DamageInfo.DamageType.NORMAL,
                AbstractGameAction.AttackEffect.SLASH_HEAVY
        ));
        this.addToBot(new ModifyDamageAction(this.uuid, this.magicNumber));
        this.addToBot(new ModifiySecondDamageAction(this.uuid, this.magicNumber));
    }

    public void upp() {
        //upgradeDamage(-4);
        //upgradeSecondDamage(12);
        upgradeMagicNumber(2);
    }
}
