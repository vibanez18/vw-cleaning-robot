package com.vw.cleaningrobot.domain.model

object RobotCleaningFactory {
    fun withStartingRobot(startingRobot: Robot): RobotCleaning = RobotCleaning(
        id = startingRobot.id,
        startingPosition = startingRobot.position,
        startingDirection = startingRobot.direction,
    )

    fun withEndingRobot(endingRobot: Robot): RobotCleaning = RobotCleaning(
        id = endingRobot.id,
        endingPosition = endingRobot.position,
        endingDirection = endingRobot.direction
    )
}