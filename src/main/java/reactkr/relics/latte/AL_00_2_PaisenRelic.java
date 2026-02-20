package reactkr.relics.latte;

import basemod.BaseMod;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import reactkr.Latte;
import reactkr.relics.AbstractEasyRelic;

import static reactkr.ModFile.makeID;
import static reactkr.util.Wiz.applyToSelf;

public class AL_00_2_PaisenRelic extends AbstractEasyRelic {
    public static final String ID = makeID("PaisenRelic");

    public AL_00_2_PaisenRelic() {
        super(ID, RelicTier.SPECIAL, LandingSound.FLAT, Latte.Enums.LATTE_COLOR);
    }

    @Override
    public void atBattleStart() {
        this.flash(); // 유물이 반짝이는 시각 효과
        AbstractPlayer p = AbstractDungeon.player;
        applyToSelf(new StrengthPower(p,1));
        applyToSelf(new DexterityPower(p,1));
        applyToSelf(new ThornsPower(p,1));
        applyToSelf(new MetallicizePower(p,1));
    }

    @Override
    public void initializeTips(){
        super.initializeTips();

        this.tips.add(new PowerTip(GameDictionary.STRENGTH.NAMES[0],
                GameDictionary.STRENGTH.DESCRIPTION));
        this.tips.add(new PowerTip(GameDictionary.DEXTERITY.NAMES[0],
                GameDictionary.DEXTERITY.DESCRIPTION));
        this.tips.add(new PowerTip(GameDictionary.THORNS.NAMES[0],
                GameDictionary.THORNS.DESCRIPTION));

        this.tips.add(new PowerTip("금속화", "매 턴 종료 시 #y방어도를 1 얻습니다."));
    }
}
