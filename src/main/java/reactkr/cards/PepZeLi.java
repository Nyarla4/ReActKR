//package reactkr.cards;
//
//import com.megacrit.cardcrawl.actions.AbstractGameAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
//import com.megacrit.cardcrawl.cards.DamageInfo;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//
//import static reactkr.ModFile.makeID;
//import static reactkr.util.Wiz.atb;
//
//public class PepZeLi extends AbstractEasyCard {
//    public final static String ID = makeID("PepZeLi");
//    // intellij stuff attack, enemy, basic, 6, 3,  , , ,
//
//    public PepZeLi() {
//        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, CardColor.COLORLESS);
//        baseMagicNumber = magicNumber = 5;
//        baseBlock = block = 7;
//    }
//
//    @Override
//    public void use(AbstractPlayer p, AbstractMonster m) {
//        atb(new DamageAction(p, new DamageInfo(p, magicNumber, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
//        blck();
//    }
//
//    @Override
//    public void upp() {
//        upgradeMagicNumber(-2);
//        upgradeBlock(2);
//    }
//}