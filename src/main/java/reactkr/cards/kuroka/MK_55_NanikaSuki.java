package reactkr.cards.kuroka;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.actions.EasyModalChoiceAction;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.cards.EasyModalChoiceCard;
import reactkr.cards.subcards.*;
import reactkr.util.ProAudio;

import java.util.ArrayList;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.*;

public class MK_55_NanikaSuki extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("NanikaSuki");
    // intellij stuff skill, self, uncommon, , , , , ,

    AbstractCard previewCard01 =new MK_55_Strawberry();
    AbstractCard previewCard02 =new MK_55_Chocomint();
    AbstractCard previewCard03 =new MK_55_Cookiecream();
    private static final Logger logger = LogManager.getLogger(MK_55_NanikaSuki.class.getName());

    public MK_55_NanikaSuki() {
        super(ID, -1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 0;
        MultiCardPreview.add(this, AbstractDungeon.player != null, this.previewCard01, this.previewCard02, this.previewCard03);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudio(ProAudio.NANIKASUKI);

        int effectCount = EnergyPanel.totalCount;

        if (this.energyOnUse != -1) {
            effectCount = this.energyOnUse;
        }
        if (p.hasRelic("Chemical X")) {
            effectCount += 2;
        }

        effectCount += magicNumber;

        if (!this.freeToPlayOnce) {
            p.energy.use(EnergyPanel.totalCount);
        }

        for (int i = 0; i < effectCount; i++) {
            int randomValue = AbstractDungeon.cardRandomRng.random(2);
            AbstractCard card;
            switch (randomValue){
                case 1:
                    card = this.previewCard02;
                break;
                case 2:
                    card = this.previewCard03;
                break;
                case 0:
                default:
                    card = this.previewCard01;
                    break;
            }
            att(new MakeTempCardInHandAction(card.makeStatEquivalentCopy(), 1));
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
        this.previewCard01.upgrade();
        this.previewCard02.upgrade();
        this.previewCard03.upgrade();
        MultiCardPreview.multiCardPreview.get(this).forEach(AbstractCard::upgrade); // here, we show upgraded smite and safeties.
    }
}
