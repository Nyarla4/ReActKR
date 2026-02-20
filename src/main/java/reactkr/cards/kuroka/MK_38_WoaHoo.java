package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.powers.LambdaPower;
import reactkr.powers.kuroka.MK_01_Majinai_Power;
import reactkr.util.ProAudio;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.*;

public class MK_38_WoaHoo extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("WoaHoo");
    // intellij stuff skill, self, common, , , , , 1, 2

    public MK_38_WoaHoo() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudio(ProAudio.KUROKA_WOAHOO);
        applyToSelf(new LambdaPower(makeID("WoaHooPower"), cardStrings.EXTENDED_DESCRIPTION[0], AbstractPower.PowerType.BUFF, false, p, magicNumber) {
            @Override
            public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
                if(info.owner == AbstractDungeon.player && damageAmount > 0 && target != null){
                    if (target.hasPower(MK_01_Majinai_Power.POWER_ID)) {
                        this.flash();
                        atb(new DrawCardAction(this.amount));
                    }
                }
            }

            @Override
            public void updateDescription() {
                description = cardStrings.EXTENDED_DESCRIPTION[1] + this.amount + cardStrings.EXTENDED_DESCRIPTION[2];
            }

            @Override
            public void stackPower(int stackAmount) {
                this.fontScale = 8.0F;
                this.amount += stackAmount;
                updateDescription();
            }
        });
    }

    public void upp() {
        upgradeBaseCost(0);
    }

}
