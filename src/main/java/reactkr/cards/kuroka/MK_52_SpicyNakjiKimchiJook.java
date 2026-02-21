package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
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
import reactkr.powers.kuroka.MK_18_SpicyNakjiKimchiJook_Power;
import reactkr.util.ProAudio;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.*;

public class MK_52_SpicyNakjiKimchiJook extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("SpicyNakjiKimchiJook");
    // intellij stuff skill, self, common, , , , , 1, 2

    public MK_52_SpicyNakjiKimchiJook() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }
//이번 턴 카드를 쓸 때마다 방어도를 2 얻습니다.
    public void use(AbstractPlayer p, AbstractMonster m) {
        //playAudio(ProAudio.KUROKA_WOAHOO);
        applyToSelf(new MK_18_SpicyNakjiKimchiJook_Power(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }

}
