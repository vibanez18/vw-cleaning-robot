package com.vw.cleaningrobot.infrastructure.db.repository

import com.vw.cleaningrobot.domain.model.Direction
import com.vw.cleaningrobot.domain.model.Position
import com.vw.cleaningrobot.domain.model.RobotCleaning
import com.vw.cleaningrobot.domain.repository.RobotCleaningDomainRepository
import com.vw.cleaningrobot.infrastructure.db.entity.RobotCleaningEntity
import org.springframework.stereotype.Component

@Component
class RobotCleaningRepository(val robotCleaningJpaRepository: RobotCleaningJpaRepository) : RobotCleaningDomainRepository {
    override fun saveOrUpdateRobot(robotCleaning: RobotCleaning): RobotCleaning {
        val saveEntity = robotCleaningJpaRepository.save(RobotCleaningEntityMapper.fromRobotCleaning(robotCleaning))

        return RobotCleaningEntityMapper.toRobotCleaning(saveEntity)
    }
}

object RobotCleaningEntityMapper {
    fun fromRobotCleaning(robotCleaning: RobotCleaning): RobotCleaningEntity = RobotCleaningEntity(
        id = robotCleaning.id,
        startingPositionX = robotCleaning.startingPosition?.x,
        startingPositionY = robotCleaning.startingPosition?.y,
        startingDirection = robotCleaning.startingDirection?.name,
        endingPositionX = robotCleaning.endingPosition?.x,
        endingPositionY = robotCleaning.endingPosition?.y,
        endingDirection = robotCleaning.endingDirection?.name
    )

    fun toRobotCleaning(entity: RobotCleaningEntity): RobotCleaning = RobotCleaning(
        id = entity.id,
        startingPosition = createPosition(entity.startingPositionX, entity.startingPositionY),
        startingDirection = createDirection(entity.startingDirection),
        endingPosition = createPosition(entity.endingPositionX, entity.endingPositionY),
        endingDirection = createDirection(entity.endingDirection)
    )

    private fun createPosition(x: Int?, y: Int?): Position? = when {
        x != null && y != null -> Position(x, y)
        else -> null
    }

    private fun createDirection(direction: String?): Direction? = when {
        direction != null -> Direction.valueOf(direction)
        else -> null
    }

}