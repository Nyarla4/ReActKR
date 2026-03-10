package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.Kuroka;
import reactkr.Latte;
import reactkr.Mayo;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.powers.kuroka.MK_20_PepZeLiPower;

import static reactkr.ModFile.makeID;

public class MK_51_PepZeLi extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("PepZeLi");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public MK_51_PepZeLi() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = block = 7;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (mayoLatteCheck()) {
            this.addToBot(new DamageAction(p, new DamageInfo(p, magicNumber)));
        }
        this.addToBot(new GainBlockAction(p, p, this.block));
        this.addToBot(new ApplyPowerAction(p, p, new MK_20_PepZeLiPower(p, magicNumber), magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public void initializeDescription() {
        if (AbstractDungeon.player != null && AbstractDungeon.currMapNode != null) {
            if(cardStrings == null){
                CardStrings strings = CardCrawlGame.languagePack.getCardStrings(this.cardID);
                this.rawDescription = strings.DESCRIPTION;
                if (mayoLatteCheck()) {
                    this.rawDescription = strings.EXTENDED_DESCRIPTION[0] + this.rawDescription;
                }
            }
            else{
                this.rawDescription = cardStrings.DESCRIPTION;
                if (mayoLatteCheck()) {
                    this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0] + this.rawDescription;
                }
            }
        }
        super.initializeDescription();
    }

    private boolean mayoLatteCheck(){
        if(AbstractDungeon.player == null)
            return false;
        return AbstractDungeon.player.chosenClass.equals(Mayo.Enums.THE_MAYO) || AbstractDungeon.player.chosenClass.equals(Latte.Enums.THE_LATTE);
    }
}