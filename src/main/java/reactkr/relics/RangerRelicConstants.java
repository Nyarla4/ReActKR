package reactkr.relics;

import java.util.Arrays;
import java.util.List;
import static reactkr.ModFile.makeID;

public class RangerRelicConstants {
    public static final String SIAH_ID   = makeID("SiahRelic");
    public static final String URU_ID    = makeID("UruRelic");
    public static final String KUROKA_ID = makeID("KurokaRelic");
    public static final String MAYO_ID   = makeID("MayoRelic");

    public static final List<String> ALL_IDS = Arrays.asList(
            SIAH_ID, URU_ID, KUROKA_ID, MAYO_ID
    );
}