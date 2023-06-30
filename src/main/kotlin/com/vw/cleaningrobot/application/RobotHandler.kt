package com.vw.cleaningrobot.application

import com.vw.cleaningrobot.domain.service.RobotDomainService

class RobotHandler(private val robotDomainService: RobotDomainService) {

    fun startCleaning(dataInput: String): String = robotDomainService.executeRobots(dataInput).map {
        "${it.endingPosition!!.x} ${it.endingPosition.y} ${it.startingDirection}\n"
    }
        .toString()
        .replace("[", "")
        .replace("]", "")
        .replace(",", "")
}