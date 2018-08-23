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
    return 0.8
}

fun getTeamPlayers(): List<Player> {
    return listOf(
            Player("Pickford", EVERTON, GKP, 5.0),
            Player("Begovic", BOURNEMOUTH, GKP, 4.5),
            Player("Cédric", SOUTHAMPTON, DEF, 4.5),
            Player("Azpilicueta", CHELSEA, DEF, 6.5),
            Player("Wan-Bissaka", CRYSTAL_PALACE, DEF, 4.0),
            Player("Holebas", WATFORD, DEF, 4.6),
            Player("Mendy", MAN_CITY, DEF, 6.2),
            Player("Mkhitaryan", ARSENAL, MID, 7.0),
            Player("Richarlison", EVERTON, MID, 86.9),
            Player("Eriksen", SPURS, MID, 9.5),
            Player("Mané", LIVERPOOL, MID, 9.6),
            Player("Stephens", BRIGHTON, MID, 4.5),
            Player("Aubameyang", ARSENAL, FWD, 11.0),
            Player("Vardy", LEICESTER, FWD, 9.0),
            Player("King", BOURNEMOUTH, FWD, 6.4))
            .sortedWith(Comparator.comparing(Player::position).thenComparing(Player::name))
}

fun getShortlistPlayers(): List<Player> {
    return listOf(
            Player("Alonso", CHELSEA, DEF, 6.6),
            Player("Mendy", MAN_CITY, DEF, 6.2),
            Player("Steve Cook", BOURNEMOUTH, DEF, 4.6),
            Player("Robertson", LIVERPOOL, DEF, 6.0),
            Player("Pereira", LEICESTER, DEF, 5.0),
            Player("Shaw", MAN_UNITED, DEF, 5.1),
            Player("Holebas", WATFORD, DEF, 4.6),
            Player("Schlupp", CRYSTAL_PALACE, DEF, 4.5),
            Player("Mané", LIVERPOOL, MID, 9.8),
            Player("Richarlison", EVERTON, MID, 6.8),
            Player("Fraser", BOURNEMOUTH, MID, 5.5),
            Player("Pedro", CHELSEA, MID, 6.6),
            Player("Walcott", EVERTON, MID, 6.5),
            Player("Aguero", MAN_CITY, FWD, 11.0),
            Player("Wilson", BOURNEMOUTH, FWD, 6.1),
            Player("Ings", SOUTHAMPTON, FWD, 5.5))
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

    return transfers.sortedWith(Comparator
            .comparing(Transfer::getOutgoingName)
            .thenComparing(Transfer::profit))
}

fun isTransferAllowed(teamPlayer: Player, shortlistPlayer: Player, team: List<Player>, shortlist: List<Player>, pot: Double): Boolean {
    return teamPlayer.position == shortlistPlayer.position &&
            teamPlayer != shortlistPlayer &&
            teamPlayer.price + pot >= shortlistPlayer.price &&
            !shortlist.contains(teamPlayer) &&
            !team.contains(shortlistPlayer) &&
            isUnderMaxPlayersPerClubThreshold(teamPlayer, shortlistPlayer, team)
}

fun isUnderMaxPlayersPerClubThreshold(teamPlayer: Player, shortlistPlayer: Player, team: List<Player>): Boolean {
    return teamPlayer.club == shortlistPlayer.club || getNumberOfTeamPlayersForClub(team, shortlistPlayer.club) < MAX_PLAYERS_PER_CLUB
}

fun getNumberOfTeamPlayersForClub(team: List<Player>, club: Club): Int {
    return team.stream().filter { it.club == club }.count().toInt()
}
