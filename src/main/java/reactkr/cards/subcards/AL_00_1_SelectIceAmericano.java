package reactkr.cards.subcards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reactkr.cards.AbstractEasyCard;

import static reactkr.ModFile.makeID;

public class AL_00_1_SelectIceAmericano extends AbstractEasyCard {
    public static final String ID = makeID("SelectIceAmericano");

    public AL_00_1_SelectIceAmericano() {
        super(ID, -2,CardType.SKILL,CardRarity.SPECIAL, CardTarget.NONE, CardColor.COLORLESS);
    }

    @Override
    public void upp() {

    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }
}
