package reactkr.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.ModFile;
import reactkr.vfx.FallingImageEffect;

import java.util.ArrayList;

import static reactkr.ModFile.makeID;

public class ApplePower extends AbstractEasyPower {

    public static final String POWER_ID = makeID("ApplePower");
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    private final Texture APPLE_IMG;

    public ApplePower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        APPLE_IMG = ImageMaster.loadImage("reactkrResources/images/effect/apple.png");
    }

    @Override
    public void onAfterCardPlayed(AbstractCard card) {
        if (card.type == AbstractCard.CardType.POWER) {
            this.flash();
            ArrayList<AbstractMonster> aliveMonsters = new ArrayList<>();

            for (AbstractMonster mon : AbstractDungeon.getMonsters().monsters) {
                if (!mon.isDeadOrEscaped()) {
                    aliveMonsters.add(mon);
                }
            }

            if (!aliveMonsters.isEmpty()) {
                int randomIndex = AbstractDungeon.miscRng.random(0, aliveMonsters.size() - 1);
                AbstractMonster randomMonster = aliveMonsters.get(randomIndex);

                if (randomMonster != null) {
                    addToBot(new VFXAction(new FallingImageEffect(randomMonster.hb.cX, randomMonster.hb.cY, APPLE_IMG), 1.0F));

                    addToBot(new DamageAction(
                            randomMonster,
                            new DamageInfo(this.owner, this.amount, DamageInfo.DamageType.THORNS)
                    ));
                }
            }
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}