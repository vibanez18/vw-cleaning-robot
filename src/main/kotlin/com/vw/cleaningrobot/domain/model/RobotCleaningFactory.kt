package com.vw.cleaningrobot.domain.model

object RobotCleaningFactory {
    fun withStartingRobot(startingRobot: Robot): RobotCleaning = RobotCleaning(
        id = startingRobot.id,
        startingPosition = startingRobot.position,
        startingDirection = startingRobot.direction,
    )

    fun withStartingRobotAndEndingRobot(startingRobot: Robot, endingRobot: Robot): RobotCleaning = RobotCleaning(
        id = endingRobot.id,
        startingPosition = startingRobot.position,
        startingDirection = startingRobot.direction,
        endingPosition = endingRobot.position,
        endingDirection = endingRobot.direction
    )
}