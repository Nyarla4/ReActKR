package reactkr.cards.subcards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.util.ProAudio;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.playAudio;

public class MM_32_Dongkk extends AbstractEasyCard_Mayo {
    public final static String ID = makeID("Dongkk");

    public MM_32_Dongkk() {
        this(false);
    }

    public MM_32_Dongkk(boolean isPreview) {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 6;
        if (!isPreview) {
            cardsToPreview = new MM_32_Chongkk(true);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudio(ProAudio.DONG);
        addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, magicNumber, false)));
        AbstractCard target = new MM_32_Chongkk();
        if(upgraded)
            target.upgrade();
        addToBot(new MakeTempCardInDrawPileAction(target.makeStatEquivalentCopy(), 1, true, true));
        //addToBot(new MakeTempCardInHandAction(target.makeStatEquivalentCopy(), 1));
    }

    @Override
    public void upp() {
        if (cardsToPreview != null) {
            cardsToPreview.upgrade();
        }
        upgradeMagicNumber(4);
    }

    @Override
    public AbstractCard makeCopy() {
        return new MM_32_Dongkk();
    }
}
