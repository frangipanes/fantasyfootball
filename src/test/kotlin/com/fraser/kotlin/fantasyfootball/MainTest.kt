package com.fraser.kotlin.fantasyfootball

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MainTest {

    private val martin: Player = Player("Martin", Club.MAN_CITY, Position.FWD, 4.5)
    private val steve: Player = Player("Steve", Club.WATFORD, Position.DEF, 5.1)

    @Test
    internal fun `Do not transfer players in different positions`() {
        assertThat(getTransfers(listOf(martin), listOf(steve))).isEmpty()
    }
}
