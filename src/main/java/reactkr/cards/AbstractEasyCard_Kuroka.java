package reactkr.cards;

import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactkr.Kuroka;
import reactkr.powers.kuroka.MK_01_Majinai_Power;
import reactkr.util.CardArtRoller;

import java.util.ArrayList;
import java.util.List;

import static reactkr.ModFile.makeID;

public abstract class AbstractEasyCard_Kuroka extends AbstractEasyCard {

    private boolean needsArtRefresh = false;

    public AbstractEasyCard_Kuroka(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target) {
        this(cardID, cost, type, rarity, target, Kuroka.Enums.KUROKA_COLOR);
    }

    public AbstractEasyCard_Kuroka(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, final CardColor color) {
        super(cardID, cost, type, rarity, target, color);
    }

    @Override
    public void update() {
        super.update();
        if (needsArtRefresh)
            CardArtRoller.computeCard(this);
    }
}
