package com.vw.cleaningrobot.domain.service

import com.vw.cleaningrobot.domain.model.Direction
import com.vw.cleaningrobot.domain.model.Position
import com.vw.cleaningrobot.domain.model.Robot
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class RobotDomainServiceTest {

    @Test
    fun `when executeRobots with happy path returns final robots` () {
        val service = RobotDomainService()

        val robots = service.executeRobots(generateHappyPathDataInput())

        assertThat(robots)
            .hasSize(2)
            .containsSequence(
                Robot(position = Position(1, 3), direction = Direction.N),
                Robot(position = Position(5, 1), direction = Direction.E)
            )

    }

    @Test
    fun `when executeRobots with invalid data throws exception` () {
        val service = RobotDomainService()

        assertThatThrownBy { service.executeRobots(generateWrongInitialPositionDataInput()) }
            .isInstanceOf(RobotDomainServiceException::class.java)
            .hasMessage("Invalid number of robots and movements")
    }

    private fun generateHappyPathDataInput() = """
        5 5
        1 2 N
        LMLMLMLMM
        3 3 E
        MMRMMRMRRM
    """.trimIndent()

    private fun generateWrongInitialPositionDataInput() = """
        5 5
        1 2 N 1 2
        LMLMLMLMM
        3 3 E
        MMRMMRMRRM
    """.trimIndent()
}