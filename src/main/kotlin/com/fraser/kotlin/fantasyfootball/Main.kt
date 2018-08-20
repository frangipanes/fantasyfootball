package com.fraser.kotlin.fantasyfootball

import com.fraser.kotlin.fantasyfootball.Club.*
import com.fraser.kotlin.fantasyfootball.Position.*
import java.util.*

// TODO: Input/output as json

fun main(args: Array<String>) {
    println("Team:")
    val teamPlayers = getTeamPlayers()
    teamPlayers.forEach(::println)

    println("\nPot:")
    val pot = getPot()
    println(pot)

    println("\nShortlist:")
    val shortlistPlayers = getShortlistPlayers()
    shortlistPlayers.forEach(::println)

    println("\nTransfers:")
    val transfers = getTransfers(teamPlayers, shortlistPlayers)
    transfers.forEach(::println)
}

fun getPot(): Double {
    return 0.0
}

fun getTeamPlayers(): List<Player> {
    return listOf(
            Player("Pickford", EVERTON, GKP, 5.0),
            Player("Cédric", SOUTHAMPTON, DEF, 4.5),
            Player("Azpilicueta", CHELSEA, DEF, 6.5),
            Player("Wan-Bissaka", CRYSTAL_PALACE, DEF, 4.0),
            Player("Tarkowski", BURNLEY, DEF, 5.0),
            Player("Mkhitaryan", ARSENAL, MID, 7.0),
            Player("Sané", MAN_CITY, MID, 9.5),
            Player("Eriksen", SPURS, MID, 9.5),
            Player("Mané", LIVERPOOL, MID, 9.6),
            Player("Aubameyang", ARSENAL, FWD, 11.0),
            Player("Vardy", LEICESTER, FWD, 9.0),
            Player("Begovic", BOURNEMOUTH, GKP, 4.5),
            Player("Peltier", CARDIFF, DEF, 4.0),
            Player("Stephens", BRIGHTON, MID, 4.5),
            Player("King", BOURNEMOUTH, FWD, 6.5))
            .sortedWith(Comparator.comparing(Player::position).thenComparing(Player::name))
}

fun getShortlistPlayers(): List<Player> {
    return listOf(
            Player("Vertonghen", SPURS, DEF, 6.0),
            Player("McCarthy", SOUTHAMPTON, GKP, 4.5),
            Player("Shaw", MAN_UNITED, DEF, 5.1),
            Player("Robertson", LIVERPOOL, DEF, 6.0),
            Player("van Aanholt", CRYSTAL_PALACE, DEF, 5.5),
            Player("Schlupp", CRYSTAL_PALACE, DEF, 4.5),
            Player("Alonso", CHELSEA, DEF, 6.5),
            Player("Fraser", BOURNEMOUTH, MID, 5.5),
            Player("Neves", WOLVES, MID, 5.1),
            Player("Wan-Bissaka", CRYSTAL_PALACE, DEF, 4.0),
            Player("Holebas", WATFORD, DEF, 4.6),
            Player("Richarlison", EVERTON, MID, 6.6),
            Player("Mendy", MAN_CITY, DEF, 6.1),
            Player("Pereyra", WATFORD, MID, 6.1),
            Player("Mané", LIVERPOOL, MID, 9.6))
            .sortedWith(Comparator.comparing(Player::position).thenComparing(Player::name))
}

fun getTransfers(team: List<Player>, shortlist: List<Player>): List<Transfer> {
    val transfers = mutableListOf<Transfer>()

    for (shortlistPlayer in shortlist) {
        for (teamPlayer in team) {
            if (isTransferAllowed(teamPlayer, shortlistPlayer, team, shortlist)) {
                transfers.add(Transfer(teamPlayer, shortlistPlayer))
            }
        }
    }

    return transfers.sortedWith(Comparator
            .comparing(Transfer::getOutgoingName)
            .thenComparing(Transfer::profit))
}

fun isTransferAllowed(teamPlayer: Player, shortlistPlayer: Player, team: List<Player>, shortlist: List<Player>): Boolean {
    return teamPlayer.position == shortlistPlayer.position
}
