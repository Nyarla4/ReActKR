package reactkr.relics;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import reactkr.Kuroka;
import reactkr.Latte;
import reactkr.Mayo;
import reactkr.actions.RelicMsgAction;
import reactkr.cards.SiahCard;
import reactkr.powers.ReactKRRangersPower;

import static reactkr.ModFile.makeID;

public class SiahRelic extends AbstractEasyRelic {
    public static final String ID = makeID("SiahRelic");

    public SiahRelic() {
        super(ID, RelicTier.RARE, LandingSound.FLAT, null);
    }

    public void onEquip() {
        AbstractCard card = new SiahCard();

        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(
                card,
                (float) Settings.WIDTH / 2.0F,
                (float) Settings.HEIGHT / 2.0F
        ));
    }

    @Override
    public void atBattleStart() {
        super.atBattleStart();
        AbstractPlayer p = AbstractDungeon.player;
        boolean isRanger =
                p.hasRelic(UruRelic.ID) &&
                        p.hasRelic(KurokaRelic.ID) &&
                        p.hasRelic(MayoRelic.ID);
        if (!isRanger) return;

        addRelicSequence(p, SiahRelic.ID, "레드 아카메 시아", Color.valueOf("C22628"));
        addRelicSequence(p, UruRelic.ID, "블루 미즈모 우루", Color.valueOf("E2F0FD"));
        addRelicSequence(p, KurokaRelic.ID, "퍼플 마지나이 쿠로카", Color.valueOf("7E7291"));
        addRelicSequence(p, MayoRelic.ID, "그린 미도미도 마요", Color.valueOf("168F43"));
        this.addToBot(new RelicMsgAction(p, "리액트KR 레인저스", Color.WHITE, 1.0f));

        this.addToBot(new ApplyPowerAction(p, p, new ReactKRRangersPower(p)));
    }

    // 가독성을 위한 헬퍼 메서드
    private void addRelicSequence(AbstractPlayer p, String relicID, String msg, Color color) {
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                p.getRelic(relicID).flash(); // 유물 반짝임
                this.isDone = true;
            }
        });
        this.addToBot(new RelicMsgAction(p, msg, color, 1.0f)); // 1초 대기하며 텍스트 출력
    }

    public AbstractRelic makeCopy() {
        return new SiahRelic();
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.chosenClass == Kuroka.Enums.THE_KUROKA ||
                AbstractDungeon.player.chosenClass == Mayo.Enums.THE_MAYO ||
                AbstractDungeon.player.chosenClass == Latte.Enums.THE_LATTE;
    }
}
