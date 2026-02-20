package reactkr.cards;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.Kuroka;
import reactkr.Latte;
import reactkr.Mayo;
import reactkr.actions.ModifiyMagicNumberAction;
import reactkr.actions.TransformCardInHandAction;
import reactkr.cards.latte.AL_99_WimpyTiger;
import reactkr.cards.mayo.MM_01_PiercingLaugh;
import reactkr.powers.kuroka.MK_01_Majinai_Power;
import reactkr.util.ProAudio;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.playAudio;

public class UtaWaku extends AbstractPreviewCard {
    public final static String ID = makeID("UtaWaku");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private float previewTimer = 0f;
    private int previewIdx = 0;
    private static final float previewTime = 2.0f;

    private static final Logger logger = LogManager.getLogger(UtaWaku.class.getName());

    private final AbstractCard cardMayoGet = new MM_01_PiercingLaugh();
    private final AbstractCard cardLatteGet = new AL_99_WimpyTiger();

    public UtaWaku() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE, CardColor.COLORLESS);
        baseBlock = block = 6;
        baseMagicNumber = magicNumber = 5;
        initializeDescription();
        if (AbstractDungeon.player != null) {
            if (AbstractDungeon.player.chosenClass.equals(Mayo.Enums.THE_MAYO)) {
                cardsToPreview = cardMayoGet;
            } else if (AbstractDungeon.player.chosenClass.equals(Latte.Enums.THE_LATTE)) {
                cardsToPreview = cardLatteGet;
            }
        }
    }

    // 백과사전 프리뷰 사이클용
    @Override
    public void update() {
        super.update();
        if (AbstractDungeon.player == null) {
            previewTimer += Gdx.graphics.getDeltaTime();

            if (previewTimer > previewTime) {
                previewTimer = 0f;
                previewIdx = (previewIdx + 1) % 3;
            }
            if (this.cardsToPreview == null) {
                this.cardsToPreview = this.makeStatEquivalentCopy();
            }
            this.cardsToPreview.name = getPreviewName(previewIdx);
            this.cardsToPreview.rawDescription = getPreviewDesc(previewIdx);
            this.cardsToPreview.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        if (p.chosenClass == Kuroka.Enums.THE_KUROKA) {
            addToBot(new HealAction(p, p, magicNumber));
            addToBot(new ModifiyMagicNumberAction(this.uuid, -2));
        } else if (p.chosenClass == Mayo.Enums.THE_MAYO) {
            if (AbstractDungeon.cardRandomRng.randomBoolean(upgraded ? 0.75f : 0.5f)) {
                playAudio(ProAudio.MAYO_KKINGKKING);
                addToBot(new MakeTempCardInHandAction(cardMayoGet));
            } else {
                playAudio(ProAudio.PIERCINGLAUGH);
            }
        } else if (p.chosenClass == Latte.Enums.THE_LATTE) {
            addToBot(new MakeTempCardInHandAction(cardLatteGet));
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(5);
        cardMayoGet.upgrade();
        cardLatteGet.upgrade();
    }

    @Override
    public void initializeDescription() {
        if (AbstractDungeon.player != null && AbstractDungeon.currMapNode != null) {
            AbstractPlayer p = AbstractDungeon.player;
            this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
            if (p.chosenClass == Kuroka.Enums.THE_KUROKA) {
                this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
            } else if (p.chosenClass == Mayo.Enums.THE_MAYO) {
                this.rawDescription += upgraded ? cardStrings.EXTENDED_DESCRIPTION[3] : cardStrings.EXTENDED_DESCRIPTION[2];
            } else if (p.chosenClass == Latte.Enums.THE_LATTE) {
                this.rawDescription += upgraded ? cardStrings.EXTENDED_DESCRIPTION[5] : cardStrings.EXTENDED_DESCRIPTION[4];
            }
        }
        super.initializeDescription();
    }

    private String getPreviewName(int idx) {
        switch (idx) {
            case 0:
                return "쿠로카";
            case 1:
                return "마요";
            case 2:
                return "라떼";
        }
        return this.name;
    }

    private String getPreviewDesc(int idx) {
        String result = cardStrings.EXTENDED_DESCRIPTION[0];
        switch (idx) {
            case 0:
                result += cardStrings.EXTENDED_DESCRIPTION[1];
                break;
            case 1:
                result += upgraded ? cardStrings.EXTENDED_DESCRIPTION[3] : cardStrings.EXTENDED_DESCRIPTION[2];
                break;
            case 2:
                result += upgraded ? cardStrings.EXTENDED_DESCRIPTION[5] : cardStrings.EXTENDED_DESCRIPTION[4];
                break;
        }
        return result;
    }
}
