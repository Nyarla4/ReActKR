package reactkr.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import reactkr.powers.ReactKRRangersPower;
import reactkr.relics.mayo.MM_01_NezmingRelic;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.atb;

public class MayoCard extends AbstractEasyCard {
    public final static String ID = makeID("Mayo");

    public MayoCard() {
        super(ID, 1, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
        baseDamage = damage = 12;
        baseMagicNumber = magicNumber = 5;
        baseSecondMagic = secondMagic = 7;
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (AbstractDungeon.player.hasPower(ReactKRRangersPower.POWER_ID)) {
            this.rawDescription = this.cardStrings.EXTENDED_DESCRIPTION[0];
        }
        this.initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (isAimed()) {
            if (p.hasPower(ReactKRRangersPower.POWER_ID)) {
                addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, secondMagic, false)));
            } else {
                addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, magicNumber, false)));
            }
        }
        atb(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public void upp() {
        updateCost(-1);
        upgradeDamage(6);
    }

    protected boolean isAimed() {
        AbstractPlayer p = AbstractDungeon.player;
        // 유물 보유 여부에 따른 거리(n) 결정 로직을 구조적으로 분리
        int threshold = p.hasRelic(MM_01_NezmingRelic.ID) ? 3 : 2;
        int curIdx = p.hand.group.indexOf(this);

        if (curIdx == -1) return false;

        int distRight = (p.hand.size() - 1) - curIdx;
        return distRight < threshold;
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = isAimed() ?
                AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy() :
                AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
    }
}
