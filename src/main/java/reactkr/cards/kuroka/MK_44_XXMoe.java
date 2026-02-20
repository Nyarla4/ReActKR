package reactkr.cards.kuroka;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.powers.kuroka.MK_10_XMoe_Power;

import static reactkr.ModFile.makeID;

public class MK_44_XXMoe extends AbstractWitchificateCard {
    public final static String ID = makeID("XXMoe");
    // intellij stuff skill, self, common, , , , , 1, 2

    public MK_44_XXMoe() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseBlock = block = 8;
        baseMagicNumber = magicNumber = 3;
        baseDamage = damage = 10;
        initializeWitchPreview();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        if(!witchificated){
            this.addToBot(new ApplyPowerAction(p, p, new MK_10_XMoe_Power(p, this.magicNumber), this.magicNumber));
        }
        else{
            dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
            // 2. [조건부 흐름] 첫 번째 공격 이후 체력을 체크하는 '익명 액션' 추가
            this.addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    // [구조적 판별] 대상이 살아있고, 남은 체력이 최대 체력의 50% 이하인가?
                    if (m != null && !m.isDying && m.currentHealth <= m.maxHealth / 2) {
                        // [흐름 2] 조건을 만족하면 두 번째 공격을 큐의 맨 위에 추가 (addToTop)
                        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
                    }
                    this.isDone = true; // 액션 종료
                }
            });
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
        upgradeDamage(4);
    }

    @Override
    void Witchification() {
        if (witchificated) {
            this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
            this.target = CardTarget.ENEMY;
        } else {
            this.rawDescription = cardStrings.DESCRIPTION;
            this.target = CardTarget.NONE;
        }
    }
}
