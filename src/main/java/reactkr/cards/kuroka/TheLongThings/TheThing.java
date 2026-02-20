package reactkr.cards.kuroka.TheLongThings;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard_Kuroka;
import reactkr.vfx.RoroblastEffect;
import reactkr.vfx.TheLongThingEffect;

import static reactkr.ModFile.makeID;

public class TheThing extends AbstractEasyCard_Kuroka {
    public final static String ID = makeID("TheThing");
    // intellij stuff Skill, Self, SPECial, , , , , ,

    public TheThing() {
        super(ID, 3, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
        baseDamage = 666;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new VFXAction(p, new TheLongThingEffect(p.dialogX, p.dialogY, p.flipHorizontal), 0.1F));
        addToBot(new DamageAllEnemiesAction(p,damage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.LIGHTNING));
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}