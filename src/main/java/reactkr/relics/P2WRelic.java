package reactkr.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.AThousandCuts;
import com.megacrit.cardcrawl.cards.green.WellLaidPlans;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.powers.watcher.MasterRealityPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.FossilizedHelix;
import com.megacrit.cardcrawl.relics.SelfFormingClay;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import reactkr.Kuroka;
import reactkr.Latte;
import reactkr.Mayo;

import static reactkr.ModFile.makeID;

public class P2WRelic extends AbstractEasyRelic implements ClickableRelic {
    public static final String ID = makeID("P2WRelic");

    private AbstractPower[] powersToApply;

    public P2WRelic() {
        super(ID, RelicTier.RARE, LandingSound.FLAT, null);
    }

    @Override
    public void atBattleStart() {
        powersToApply = new AbstractPower[]{
                new StrengthPower(AbstractDungeon.player, 1),//힘
                new PlatedArmorPower(AbstractDungeon.player, 1),//판금 갑옷
                new MetallicizePower(AbstractDungeon.player, 1),//금속화
                new ArtifactPower(AbstractDungeon.player, 1),//인공물
                new BarricadePower(AbstractDungeon.player),//바리케이드
                new ThornsPower(AbstractDungeon.player, 1),//가시
                new IntangiblePower(AbstractDungeon.player, 1),//불가침
                new RitualPower(AbstractDungeon.player, 1, true),//의식(매 턴 힘n)
                new DexterityPower(AbstractDungeon.player, 1),//민첩
                new EnergizedPower(AbstractDungeon.player, 1),//혈기왕성(다음턴 추가 에너지)
                new DemonFormPower(AbstractDungeon.player, 1),//악마화
                new RupturePower(AbstractDungeon.player, 1),//파열(카드 효과로 받은 피해만큼 힘 획득)
                new CombustPower(AbstractDungeon.player, 1, 1),//연소(턴 종료시 체력 n 잃고 적 전체 m피해)
                new BrutalityPower(AbstractDungeon.player, 1),//무자비(턴 시작시 잃은 체력만큼 드로우)
                new FeelNoPainPower(AbstractDungeon.player, 1),//무감각(카드 소멸시 방어도)
                new CorruptionPower(AbstractDungeon.player),//타락(스킬 카드 0코, 사용시 소멸)
                new DarkEmbracePower(AbstractDungeon.player, 1),//어둠의 포옹(카드 소멸시 드로우)
                new FireBreathingPower(AbstractDungeon.player, 1),//불 뿜기(상태이상이나 저주 뽑으면 적 전체 피해)
                new EvolvePower(AbstractDungeon.player, 1),//진화(상태이상이나 저주 뽑으면 드로우)
                new JuggernautPower(AbstractDungeon.player, 1),//절대적인 힘(방어도 얻으면 무작위 적 피해)
                new RagePower(AbstractDungeon.player, 1),//격노(공격 카드 쓸 때마다 방어도)
                new DoubleTapPower(AbstractDungeon.player, 1),//연사
                new FlameBarrierPower(AbstractDungeon.player, 1),//화염 장벽(피격시 공격대상에게 피해)
                new NextTurnBlockPower(AbstractDungeon.player, 1),//다음 턴 방어(자가 점토나 구르기)
                new PanachePower(AbstractDungeon.player, 1),//위풍당당(턴에 5장 사용시 적 전체 피해)
                new SadisticPower(AbstractDungeon.player, 1),//가학적 본능(해로운 효과 부여 시 추가 피해)
                new BlurPower(AbstractDungeon.player, 1),//흐릿함(다음 턴 시작시 방어도 잃지않음)
                new BurstPower(AbstractDungeon.player, 1),//폭주(연사 스킬 버전)
                new DoubleDamagePower(AbstractDungeon.player, 1, false),//두 배의 피해(환영 살인마)
                new NoxiousFumesPower(AbstractDungeon.player, 1),//유독 가스
                new RetainCardPower(AbstractDungeon.player, 1),//카드 보존(괜찮은 전략)
                new ThousandCutsPower(AbstractDungeon.player, 1),//능지처참(카드 사용 시 적 전체 피해)
                new AfterImagePower(AbstractDungeon.player, 1),//잔상(카드 사용 시 방어도)
                new EnvenomPower(AbstractDungeon.player, 1),//독 바르기
                new PenNibPower(AbstractDungeon.player, 1),//펜 촉(다음 1회 공격 카드 피해 2배)
                new EquilibriumPower(AbstractDungeon.player, 1),//평형(이번 턴 종료시 카드 버리지 않음)
                new EchoPower(AbstractDungeon.player, 1),//메아리(턴 첫 카드 2번)
                new BufferPower(AbstractDungeon.player, 1),//버퍼(체력 잃음 1회 방어)
                new MasterRealityPower(AbstractDungeon.player),//현실지배(전투중 생성되는 카드 강화)
                new CollectPower(AbstractDungeon.player, 1),//수집(턴 시작시 기적+ 획득)
        };
    }

    public AbstractRelic makeCopy() {
        return new P2WRelic();
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.chosenClass == Kuroka.Enums.THE_KUROKA ||
                AbstractDungeon.player.chosenClass == Mayo.Enums.THE_MAYO ||
                AbstractDungeon.player.chosenClass == Latte.Enums.THE_LATTE;
    }

    @Override
    public void onRightClick() {
        // 1. 전투 중인지 확인
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {

            AbstractPlayer p = AbstractDungeon.player;
            int randomValue = AbstractDungeon.cardRandomRng.random(powersToApply.length);
            AbstractPower powerToApply = powersToApply[randomValue];

            // 2. 액션 실행
            this.flash(); // 유물 반짝임
            if (powerToApply != null)
                addToBot(new ApplyPowerAction(p, p, powerToApply));
            else
                addToBot(new GainBlockAction(p, 1));//방어도
        }
    }
}