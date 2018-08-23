package com.fraser.kotlin.fantasyfootball

import java.util.*

data class Player(val name: String, val club: Club, val position: Position, val price: Double) {

    /**
     * Custom equals method which ignores price
     */
    override fun equals(other: Any?): Boolean {
        if (other == null || javaClass != other.javaClass) return false
        val that = other as Player?
        return name == that!!.name &&
                club == that.club &&
                position == that.position
    }

    /**
     * Custom hashcode method which ignores price
     */
    override fun hashCode(): Int {
        return Objects.hash(name, club, position)
    }
}

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