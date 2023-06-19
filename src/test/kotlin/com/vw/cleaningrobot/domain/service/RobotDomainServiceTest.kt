package com.vw.cleaningrobot.domain.service

import com.vw.cleaningrobot.domain.model.Direction
import com.vw.cleaningrobot.domain.model.Position
import com.vw.cleaningrobot.domain.model.RobotCleaning
import com.vw.cleaningrobot.domain.repository.RobotCleaningDomainRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
class RobotDomainServiceTest {

    @InjectMocks
    lateinit var robotDomainService: RobotDomainService

    @Mock
    lateinit var robotCleaningDomainRepository: RobotCleaningDomainRepository

    @Test
    fun `when executeRobots with happy path returns final robots`() {
        whenever(robotCleaningDomainRepository.saveOrUpdateRobot(RobotCleaning(startingPosition = Position(1, 2), startingDirection = Direction.N)))
            .thenReturn(RobotCleaning(id = 1, startingPosition = Position(1, 2), startingDirection = Direction.N))
        whenever(robotCleaningDomainRepository.saveOrUpdateRobot(RobotCleaning(startingPosition = Position(3, 3), startingDirection = Direction.E)))
            .thenReturn(RobotCleaning(id = 2, startingPosition = Position(3, 3), startingDirection = Direction.E))

        whenever(
            robotCleaningDomainRepository.saveOrUpdateRobot(
                RobotCleaning(
                    id = 1,
                    startingPosition = Position(1, 2),
                    startingDirection = Direction.N,
                    endingPosition = Position(1, 3),
                    endingDirection = Direction.N
                )
            )
        )
            .thenReturn(
                RobotCleaning(
                    id = 1,
                    startingPosition = Position(1, 2),
                    startingDirection = Direction.N,
                    endingPosition = Position(1, 3),
                    endingDirection = Direction.N
                )
            )
        whenever(
            robotCleaningDomainRepository.saveOrUpdateRobot(
                RobotCleaning(
                    id = 2,
                    startingPosition = Position(3, 3),
                    startingDirection = Direction.E,
                    endingPosition = Position(5, 1),
                    endingDirection = Direction.E
                )
            )
        )
            .thenReturn(
                RobotCleaning(
                    id = 2,
                    startingPosition = Position(3, 3),
                    startingDirection = Direction.E,
                    endingPosition = Position(5, 1),
                    endingDirection = Direction.E
                )
            )

        val robots = robotDomainService.executeRobots(generateHappyPathDataInput())

        assertThat(robots)
            .hasSize(2)
            .containsSequence(
                RobotCleaning(
                    id = 1,
                    startingPosition = Position(1, 2),
                    startingDirection = Direction.N,
                    endingPosition = Position(1, 3),
                    endingDirection = Direction.N
                ),
                RobotCleaning(
                    id = 2,
                    startingPosition = Position(3, 3),
                    startingDirection = Direction.E,
                    endingPosition = Position(5, 1),
                    endingDirection = Direction.E
                )
            )

    }

    @Test
    fun `when executeRobots with invalid data throws exception`() {
        assertThatThrownBy { robotDomainService.executeRobots(generateWrongInitialPositionDataInput()) }
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