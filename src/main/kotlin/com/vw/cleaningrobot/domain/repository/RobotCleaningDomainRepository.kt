package com.vw.cleaningrobot.domain.repository

import com.vw.cleaningrobot.domain.model.RobotCleaning

interface RobotCleaningDomainRepository {
    fun saveOrUpdateRobot(robotCleaning: RobotCleaning): RobotCleaning
}