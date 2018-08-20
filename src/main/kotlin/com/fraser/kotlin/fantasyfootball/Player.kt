package com.fraser.kotlin.fantasyfootball

data class Player(val name: String, val club: Club, val position: Position, val price: Double)

enum class Position {
    GKP,
    DEF,
    MID,
    FWD
}

enum class Club {
    BOURNEMOUTH,
    ARSENAL,
    BRIGHTON,
    BURNLEY,
    CARDIFF,
    CHELSEA,
    CRYSTAL_PALACE,
    EVERTON,
    FULHAM,
    HUDDERSFIELD,
    LEICESTER,
    LIVERPOOL,
    MAN_CITY,
    MAN_UNITED,
    NEWCASTLE,
    SOUTHAMPTON,
    SPURS,
    WATFORD,
    WEST_HAM,
    WOLVES
}