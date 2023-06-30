package com.vw.cleaningrobot.infrastructure.rest

import com.vw.cleaningrobot.application.RobotHandler
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cleaning")
class RobotController(val robotHandler: RobotHandler) {

    @PostMapping("/start", produces = [MediaType.TEXT_PLAIN_VALUE], consumes = [MediaType.TEXT_PLAIN_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun startCleaning(@RequestBody dataInput: String): String = robotHandler.startCleaning(dataInput)
}