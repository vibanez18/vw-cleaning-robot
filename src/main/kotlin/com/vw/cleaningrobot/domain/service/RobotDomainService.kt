package com.vw.cleaningrobot.domain.service

import com.vw.cleaningrobot.domain.model.*
import com.vw.cleaningrobot.domain.repository.RobotCleaningDomainRepository
import com.vw.cleaningrobot.logger
import org.springframework.stereotype.Service
import java.util.regex.Pattern

@Service
class RobotDomainService(val robotCleaningRepository: RobotCleaningDomainRepository) {

    companion object {
        const val LINE_DELIMITER = " "
        val VALID_LINE_ROBOT: Pattern = Pattern.compile("^\\d \\d [NSEW]", Pattern.DOTALL)
        val VALID_LINE_MOVEMENTS_ROBOT: Pattern = Pattern.compile("[LMR]*\$", Pattern.DOTALL)
    }

    fun executeRobots(dataInput: String): List<RobotCleaning> {
        val lines = dataInput.split("\n")
        val validatedLines = validateLines(lines)

        return validatedLines
            .chunked(2) { (position, moves) -> executeRobot(lineToRobot(position), lineToMoves(moves)) }
            .toList()
    }

    private fun executeRobot(robot: Robot, moves: List<Move>): RobotCleaning {
        val moveListIterator = moves.listIterator()
        var robotMoved: Robot? = null

        while (moveListIterator.hasNext()) {
            robotMoved = if (robotMoved != null) {
                moveRobot(moveListIterator.next(), robotMoved)
                    .apply { logger().debug("Robot moved: $this") }
            } else {
                moveRobot(moveListIterator.next(), robot)
                    .apply { logger().debug("Robot moved: $this") }
            }
        }

        val saveOrUpdateRobot = robotCleaningRepository.saveOrUpdateRobot(RobotCleaningFactory.withEndingRobot(robotMoved!!))

        logger().info("Robot started at: (${saveOrUpdateRobot.startingPosition?.x},${saveOrUpdateRobot.startingPosition?.y})${saveOrUpdateRobot.startingDirection} and finished at: (${saveOrUpdateRobot.endingPosition?.x},${saveOrUpdateRobot.endingPosition?.y})${saveOrUpdateRobot.endingDirection}")

        return saveOrUpdateRobot
    }

    private fun moveRobot(move: Move, robot: Robot): Robot = when (move) {
        Move.L -> robot.moveLeft()
        Move.R -> robot.moveRight()
        Move.M -> robot.moveForward()
    }


    private fun validateLines(lines: List<String>): List<String> =
        lines
            .filter { VALID_LINE_ROBOT.matcher(it).matches() || VALID_LINE_MOVEMENTS_ROBOT.matcher(it).matches() }
            .apply { if (this.size % 2 != 0) throw RobotDomainServiceException("Invalid number of robots and movements") }


    private fun lineToRobot(position: String): Robot {
        val stringPositions = position.split(LINE_DELIMITER)

        val robot = Robot(
            position = Position(stringPositions[0][0].digitToInt(), stringPositions[1][0].digitToInt()),
            direction = Direction.valueOf(stringPositions[2])
        )

        val saveRobot = robotCleaningRepository.saveOrUpdateRobot(RobotCleaningFactory.withStartingRobot(robot))

        return robot.copy(id = saveRobot.id)
    }

    private fun lineToMoves(moves: String): List<Move> = moves.map { it.toMove() }
}

class RobotDomainServiceException(msg: String) : RuntimeException(msg)

fun Char.toMove(): Move = Move.valueOf(this.toString())