package com.vw.cleaningrobot.domain.service

import com.vw.cleaningrobot.domain.model.Direction
import com.vw.cleaningrobot.domain.model.Move
import com.vw.cleaningrobot.domain.model.Position
import com.vw.cleaningrobot.domain.model.Robot
import org.springframework.stereotype.Service
import java.util.regex.Pattern

@Service
class RobotDomainService {

    companion object {
        val VALID_LINE_ROBOT: Pattern = Pattern.compile("^\\d \\d [NSEW]", Pattern.DOTALL)
        val VALID_LINE_MOVEMENTS_ROBOT: Pattern = Pattern.compile("[LMR]*\$", Pattern.DOTALL)
    }

    fun executeRobots(dataInput: String): List<Robot> {
        val lines = dataInput.split("\n")
        val validatedLines = validateLines(lines)

        return validatedLines
            .chunked(2) { (position, moves) -> executeRobot(lineToRobot(position), lineToMoves(moves)) }
            .toList()
    }

    private fun executeRobot(robot: Robot, moves: List<Move>): Robot {
        val moveListIterator = moves.listIterator()
        var robotMoved: Robot? = null


        while (moveListIterator.hasNext()) {
            robotMoved = if(robotMoved != null) {
                moveRobot(moveListIterator.next(), robotMoved)
            } else {
                moveRobot(moveListIterator.next(), robot)
            }
        }

        return robotMoved!!
    }

    private fun moveRobot(move: Move, robot: Robot): Robot = when (move) {
        Move.L -> robot.moveLeft()
        Move.R -> robot.moveRight()
        Move.M -> robot.moveForward()
    }


    private fun validateLines(lines: List<String>): List<String> =
        lines.filter { VALID_LINE_ROBOT.matcher(it).matches() || VALID_LINE_MOVEMENTS_ROBOT.matcher(it).matches() }


    private fun lineToRobot(position: String): Robot {
        val stringPositions = position.split(" ")

        // TODO: add validations

        return Robot(
            position = Position(stringPositions[0][0].digitToInt(), stringPositions[1][0].digitToInt()),
            direction = Direction.valueOf(stringPositions[2])
        )
    }

    private fun lineToMoves(moves: String): List<Move> = moves.map { it.toMove() }
}

fun Char.toMove(): Move = Move.valueOf(this.toString())