package xyz.qwewqa.relive.simulator.core.presets.memoir

val memoirs = mapOf("None" to EmptyMemoir) + listOf(
    UnshakableFeelings,
    FriendsAtTheAquarium,
    UrashimaTaroPerformanceFlyer,
    CoStarringWithHatsuneMiku,
    FirstAnnivSeishoMusicAcademy,
    FirstAnnivSiegfeldInstituteOfMusic,
    FirstAnnivRinmeikanGirlsSchool,
    FirstAnnivFrontierSchoolOfArts,
    BlessedDawn,
    BandsmansGreeting,
    KeepersOfThePeace,
    CrazyMadScientist,
    SunnyLunchtime,
    ToTheWonderfulWorldOfRakugo,
    ThePhantomAndChristine,
    PoolsideIncident,
    MerryChristmas2019,
    CherryBlossomsInTheBento,
    PrinceAndPrincessEtude,
    StarOfTheDay,
    WrongStarOfTheDay,
    SunsetLabMemBadge,
    ReminiscenceMelody,
    KappoTomoyesPosterGirl,
    UnburnedFlowerUnwitheredFlame,
    XIIHangedManReverse,
    VILoversReverse,
    XVITowerUpright,
    XVITowerReverse,
    TheGreatYearEndCleanup,
    ConfidantsOnADate,
    SparklingStageChika,
    StretchingHelp,
    ReverberatingVoiceTsubasaMaya,
    ChinatownDelicacies,
    DeuxJunoJuneBride,
    EnjoyingWinter,
    TurbulentNinja,
    ADayInTheGodessesLife,
    CleaningTogether,
    RareCoStar,
    OneForAll,
    StarOfTheDayFutabaKaoruko,
    AimToUnifyTheWorld,
    ForTheShinsengumi,
    ShinsengumiRinpuden,
).sortedBy { it.name }.sortedBy { it.cutinData == null }.associateBy { it.name }
