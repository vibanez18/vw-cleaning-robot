package com.vw.cleaningrobot.domain.service

import com.vw.cleaningrobot.domain.model.*
import com.vw.cleaningrobot.domain.repository.RobotCleaningDomainRepository
import com.vw.cleaningrobot.logger
import org.springframework.stereotype.Service
import java.util.regex.Pattern

@Service
class RobotDomainService(val robotCleaningRepository: RobotCleaningDomainRepository) {

    companion object {
        val VALID_WORKSPACE: Pattern = Pattern.compile("^[123456789][123456789]", Pattern.DOTALL)
        val VALID_LINE_ROBOT: Pattern = Pattern.compile("^\\d\\d[NSEW]", Pattern.DOTALL)
        val VALID_LINE_MOVEMENTS_ROBOT: Pattern = Pattern.compile("[LMR]*\$", Pattern.DOTALL)
    }

    fun executeRobots(dataInput: String): List<RobotCleaning> {
        val lines = dataInput.split("\n")
        val validWorkspace = validateWorkSpaceLine(lines[0])
        val robotLines = lines.subList(1, lines.count())
        val validatedRobotLines = validateRobotLines(robotLines)

        return validatedRobotLines
            .chunked(2) { (position, moves) -> executeRobot(lineToRobot(position), lineToMoves(moves), validWorkspace) }
            .toList()
    }

    private fun executeRobot(robot: Robot, moves: List<Move>, workspace: Position): RobotCleaning {
        val moveListIterator = moves.listIterator()
        var robotMoved = robot

        while (moveListIterator.hasNext()) {
            robotMoved = moveRobot(moveListIterator.next(), robotMoved, workspace)
                .apply { logger().debug("Robot moved: $this") }
        }

        val saveOrUpdateRobot = robotCleaningRepository.saveOrUpdateRobot(RobotCleaningFactory.withStartingRobotAndEndingRobot(robot, robotMoved))

        logger().info(
            "Robot started at: (${saveOrUpdateRobot.startingPosition?.x},${saveOrUpdateRobot.startingPosition?.y})${saveOrUpdateRobot.startingDirection} " +
                    "and finished at: (${saveOrUpdateRobot.endingPosition?.x},${saveOrUpdateRobot.endingPosition?.y})${saveOrUpdateRobot.endingDirection}"
        )

        return saveOrUpdateRobot
    }

    private fun moveRobot(move: Move, robot: Robot, workspace: Position): Robot = when (move) {
        Move.L -> robot.moveLeft()
        Move.R -> robot.moveRight()
        Move.M -> robot.moveForward(workspace.x, workspace.y)
    }

    private fun validateWorkSpaceLine(line: String): Position {
        val replacesLine = line.filterNot { it.isWhitespace() }
        return when {
            VALID_WORKSPACE.matcher(replacesLine).matches() -> Position(replacesLine[0].digitToInt(), replacesLine[1].digitToInt())
            else -> throw RobotDomainServiceException("Invalid workspace")
        }
    }

    private fun validateRobotLines(lines: List<String>): List<String> {
        val stringList = lines.map { line -> line.filterNot { it.isWhitespace() } }

        stringList.forEach {
            if (!VALID_LINE_ROBOT.matcher(it).matches() && !VALID_LINE_MOVEMENTS_ROBOT.matcher(it).matches()) {
                throw RobotDomainServiceException("Invalid number of robots and movements")
            }
        }
        return stringList
    }

    private fun lineToRobot(position: String): Robot {
        val robot = Robot(
            position = Position(position[0].digitToInt(), position[1].digitToInt()),
            direction = Direction.valueOf(position[2].toString())
        )

        val saveRobot = robotCleaningRepository.saveOrUpdateRobot(RobotCleaningFactory.withStartingRobot(robot))

        return robot.copy(id = saveRobot.id)
    }

    private fun lineToMoves(moves: String): List<Move> = moves.map { it.toMove() }
}

class RobotDomainServiceException(msg: String) : RuntimeException(msg)

fun Char.toMove(): Move = Move.valueOf(this.toString())