package reactkr.relics;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import reactkr.actions.RelicMsgAction;
import reactkr.cards.SiahCard;
import reactkr.powers.ReactKRRangersPower;

import static reactkr.relics.RangerRelicConstants.SIAH_ID;

public class SiahRelic extends AbstractRangerRelic {
    public static final String ID = SIAH_ID;

    public SiahRelic() { super(ID); }

    @Override
    protected AbstractCard createCard() { return new SiahCard(); }

    @Override
    public void atBattleStart() {
        super.atBattleStart();
        AbstractPlayer p = AbstractDungeon.player;
        boolean isRanger = RangerRelicConstants.ALL_IDS.stream()
                .allMatch(id -> p.hasRelic(id));
        if (!isRanger) return;

        addRelicSequence(p, SIAH_ID,   "레드 아카메 시아",      Color.valueOf("C22628"));
        addRelicSequence(p, RangerRelicConstants.URU_ID,    "블루 미즈모 우루",      Color.valueOf("E2F0FD"));
        addRelicSequence(p, RangerRelicConstants.KUROKA_ID, "퍼플 마지나이 쿠로카",  Color.valueOf("7E7291"));
        addRelicSequence(p, RangerRelicConstants.MAYO_ID,   "그린 미도미도 마요",    Color.valueOf("168F43"));
        this.addToBot(new RelicMsgAction(p, "리액트KR 레인저스", Color.WHITE, 1.0f));
        this.addToBot(new ApplyPowerAction(p, p, new ReactKRRangersPower(p)));
    }

    private void addRelicSequence(AbstractPlayer p, String relicID, String msg, Color color) {
        this.addToBot(new AbstractGameAction() {
            @Override public void update() {
                p.getRelic(relicID).flash();
                this.isDone = true;
            }
        });
        this.addToBot(new RelicMsgAction(p, msg, color, 1.0f));
    }

    @Override
    public AbstractRelic makeCopy() { return new SiahRelic(); }
}