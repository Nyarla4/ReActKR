package reactkr.relics.mayo;

import basemod.BaseMod;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import reactkr.Kuroka;
import reactkr.Latte;
import reactkr.Mayo;
import reactkr.actions.SelectAndResetMagicNumberAction;
import reactkr.cards.mayo.AbstractAimedCard;
import reactkr.relics.AbstractEasyRelic;

import static reactkr.ModFile.makeID;

public class MM_03_BanRelic extends AbstractEasyRelic {
    public static final String ID = makeID("BanRelic");

    public MM_03_BanRelic() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT, Mayo.Enums.MAYO_COLOR);
    }

    public AbstractRelic makeCopy() {
        return new MM_03_BanRelic();
    }

    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public void initializeTips() {
        super.initializeTips();

        String keyword = makeID("궁극기");
        this.tips.add(new PowerTip(BaseMod.getKeywordProper(keyword),
                BaseMod.getKeywordDescription(keyword)));
    }
}
