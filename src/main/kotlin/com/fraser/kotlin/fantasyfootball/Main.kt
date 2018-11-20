package com.fraser.kotlin.fantasyfootball

import com.fraser.kotlin.fantasyfootball.Club.*
import com.fraser.kotlin.fantasyfootball.Position.*
import java.util.*

// TODO: Input/output as json

const val MAX_PLAYERS_PER_CLUB: Int = 3

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
    val transfers = getTransfers(teamPlayers, shortlistPlayers, pot)
    transfers.forEach(::println)
}

fun getPot(): Double {
    return 0.3
}

fun getTeamPlayers(): List<Player> {
    return listOf(
        Player("Pickford", EVERTON, GKP, 4.9),
        Player("Begovic", BOURNEMOUTH, GKP, 4.5),
        Player("Femenia", WATFORD, DEF, 4.2),
        Player("Robertson", LIVERPOOL, DEF, 6.4),
        Player("Wan-Bissaka", CRYSTAL_PALACE, DEF, 4.1),
        Player("Doherty", WOLVES, DEF, 4.8),
        Player("Mendy", MAN_CITY, DEF, 6.3),
        Player("Mkhitaryan", ARSENAL, MID, 6.8),
        Player("Richarlison", EVERTON, MID, 6.8),
        Player("Eriksen", SPURS, MID, 9.2),
        Player("Mané", LIVERPOOL, MID, 9.6),
        Player("Stephens", BRIGHTON, MID, 4.4),
        Player("Aguero", MAN_CITY, FWD, 11.3),
        Player("Vardy", LEICESTER, FWD, 9.0),
        Player("King", BOURNEMOUTH, FWD, 6.4)
    )
        .sortedWith(Comparator.comparing(Player::position).thenComparing(Player::name))
}

fun getShortlistPlayers(): List<Player> {
    return listOf(
        Player("Alonso", CHELSEA, DEF, 7.0),
        Player("Mendy", MAN_CITY, DEF, 6.2),
        Player("Digne", EVERTON, DEF, 4.8),
        Player("Robertson", LIVERPOOL, DEF, 6.3),
        Player("Maguire", LEICESTER, DEF, 5.5),
        Player("Balbuena", WEST_HAM, DEF, 4.3),
        Player("Keane", EVERTON, DEF, 4.9),
        Player("Trippier", SPURS, DEF, 6.3),
        Player("Femenia", WATFORD, DEF, 4.2),
        Player("Doherty", WOLVES, DEF, 4.8),

        Player("Hazard", CHELSEA, MID, 11.3),
        Player("Salah", LIVERPOOL, MID, 13.0),
        Player("Sterling", MAN_CITY, MID, 11.1),
        Player("Mahrez", MAN_CITY, MID, 8.6),
        Player("Silva", MAN_CITY, MID, 8.5),
        Player("Willian", CHELSEA, MID, 7.4),
        Player("Sigurdsson", EVERTON, MID, 7.4),
        Player("Maddison", LEICESTER, MID, 7.0),
        Player("Pereyra", WATFORD, MID, 6.4),

        Player("Aguero", MAN_CITY, FWD, 11.3),
        Player("Arnautovic", WEST_HAM, FWD, 7.0),
        Player("Vardy", LEICESTER, FWD, 9.0),
        Player("Mitrovic", FULHAM, FWD, 6.9),
        Player("Wilson", BOURNEMOUTH, FWD, 6.6),
        Player("Lukaku", MAN_UNITED, FWD, 10.8),
        Player("Success", WATFORD, FWD, 4.5)
    )
        .sortedWith(Comparator.comparing(Player::position).thenComparing(Player::name))
}

fun getTransfers(team: List<Player>, shortlist: List<Player>, pot: Double): List<Transfer> {
    val transfers = mutableListOf<Transfer>()

    for (shortlistPlayer in shortlist) {
        for (teamPlayer in team) {
            if (isTransferAllowed(teamPlayer, shortlistPlayer, team, shortlist, pot)) {
                transfers.add(Transfer(teamPlayer, shortlistPlayer))
            }
        }
    }

    return transfers.sortedWith(
        Comparator
            .comparing(Transfer::getOutgoingName)
            .thenComparing(Transfer::profit)
    )
}

fun isTransferAllowed(
    teamPlayer: Player,
    shortlistPlayer: Player,
    team: List<Player>,
    shortlist: List<Player>,
    pot: Double
): Boolean {
    return teamPlayer.position == shortlistPlayer.position &&
            teamPlayer != shortlistPlayer &&
            teamPlayer.price + pot >= shortlistPlayer.price &&
            !shortlist.contains(teamPlayer) &&
            !team.contains(shortlistPlayer) &&
            isUnderMaxPlayersPerClubThreshold(teamPlayer, shortlistPlayer, team)
}

fun isUnderMaxPlayersPerClubThreshold(
    teamPlayer: Player,
    shortlistPlayer: Player,
    team: List<Player>
): Boolean {
    return teamPlayer.club == shortlistPlayer.club || getNumberOfTeamPlayersForClub(
        team,
        shortlistPlayer.club
    ) < MAX_PLAYERS_PER_CLUB
}

fun getNumberOfTeamPlayersForClub(team: List<Player>, club: Club): Int {
    return team.stream().filter { it.club == club }.count().toInt()
}
