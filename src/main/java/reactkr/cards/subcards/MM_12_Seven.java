package reactkr.cards.subcards;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import reactkr.actions.EasyModalChoiceAction;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.cards.EasyModalChoiceCard;
import reactkr.util.ProAudio;

import java.util.ArrayList;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.*;

public class MM_12_Seven extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("Seven");
    // intellij stuff skill, self, uncommon, , , , , ,

    public MM_12_Seven() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE);
        baseMagicNumber = magicNumber = 7;
        baseSecondMagic = secondMagic = 7;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudio(ProAudio.SASI);
        if(!upgraded){
            addToBot(new DamageAction(p, new DamageInfo(p, magicNumber)));
        }
        applyToSelf(new StrengthPower(p, secondMagic));
    }

    @Override
    public void upp() {

    }
}
