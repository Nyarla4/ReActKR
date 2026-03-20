package reactkr.cards.mayo;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.actions.EasyModalChoiceAction;
import reactkr.cards.AbstractEasyCard_Mayo;
import reactkr.cards.EasyModalChoiceCard;
import reactkr.cards.subcards.MM_52_MayoKuroka;
import reactkr.cards.subcards.MM_52_MayoSiah;
import reactkr.cards.subcards.MM_52_MayoUru;
import reactkr.powers.mayo.MM_13_ReflectPower;

import java.util.ArrayList;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.atb;
import static reactkr.util.Wiz.att;

public class MM_53_Orisa extends AbstractAimedCard implements IUltCard {
    public final static String ID = makeID("Orisa");

    public MM_53_Orisa() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 2;
        baseSecondDamage = secondDamage = 4;
        baseBlock = block = 50;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
        this.handleUltFlow(p, m);
    }

    @Override
    public void normalUse(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < 3; i++) {
            addToBot(new DamageAction(m, new DamageInfo(p, finalDamage)));
        }
    }

    @Override
    public boolean ultUse(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new ApplyPowerAction(p, p, new MM_13_ReflectPower(p, 1)));
        return true;
    }

    @Override
    public void upp() {
    }

    @Override
    public void triggerOnGlowCheck() {
        if (ultCharged()) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            super.triggerOnGlowCheck();
        }
    }
}
