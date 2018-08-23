package com.fraser.kotlin.fantasyfootball

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MainTest {

    private val martin: Player = Player("Martin", Club.MAN_CITY, Position.FWD, 0.0)
    private val gary: Player = Player("Gary", Club.MAN_CITY, Position.MID, 0.0)
    private val steve: Player = Player("Steve", Club.MAN_CITY, Position.DEF, 0.0)
    private val expensiveSteve: Player = Player("ExpensiveSteve", Club.MAN_CITY, Position.DEF, 7.5)
    private val brian: Player = Player("Brian", Club.WOLVES, Position.GKP, 0.0)
    private val michael: Player = Player("Michael", Club.MAN_CITY, Position.GKP, 0.0)
    private val barry: Player = Player("Barry", Club.MAN_CITY, Position.GKP, 0.0)

    @Test
    internal fun `Do not transfer players in different positions`() {
        assertThat(getTransfers(listOf(martin), listOf(steve), 0.0)).isEmpty()
    }

    @Test
    internal fun `Do not transfer players if I would go into overdraft`() {
        assertThat(getTransfers(listOf(steve), listOf(expensiveSteve), 1.0)).isEmpty()
    }

    @Test
    internal fun `Do transfer players if I break even`() {
        assertThat(getTransfers(listOf(steve), listOf(expensiveSteve), 7.5)).isEqualTo(listOf(Transfer(steve, expensiveSteve)))
    }

    @Test
    internal fun `Do not transfer players if it results in team with more than three players from one club`() {
        assertThat(getTransfers(listOf(brian, martin, gary, steve), listOf(michael), 0.0)).isEmpty()
    }

    @Test
    internal fun `Do transfer players if from same club`() {
        assertThat(getTransfers(listOf(martin, gary, barry), listOf(michael), 0.0)).isEqualTo(listOf(Transfer(barry, michael)))
    }

    @Test
    internal fun `Do not transfer identical players`() {
        assertThat(getTransfers(listOf(martin), listOf(martin), 0.0)).isEmpty()
    }

    @Test
    internal fun `Do not buy a player already in the team`() {
        assertThat(getTransfers(listOf(steve, michael), listOf(steve, brian), 0.0)).isEqualTo(listOf(Transfer(michael, brian)))
    }

    @Test
    internal fun `Do not sell a player already in the shortlist`() {
        assertThat(getTransfers(listOf(steve), listOf(steve, michael), 0.0)).isEmpty()
    }
}
