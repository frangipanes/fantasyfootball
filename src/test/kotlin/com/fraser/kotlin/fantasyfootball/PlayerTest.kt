package com.fraser.kotlin.fantasyfootball

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PlayerTest {

    private val steve: Player = Player("Steve", Club.MAN_CITY, Position.DEF, 0.0)
    private val expensiveSteve: Player = Player("Steve", Club.MAN_CITY, Position.DEF, 7.5)

    @Test
    internal fun `Players are equal if only prices are different`() {
        Assertions.assertThat(steve).isEqualTo(expensiveSteve)
    }
}