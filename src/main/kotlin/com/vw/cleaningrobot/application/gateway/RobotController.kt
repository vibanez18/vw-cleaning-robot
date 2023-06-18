package com.vw.cleaningrobot.application.gateway

import com.vw.cleaningrobot.domain.service.RobotDomainService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cleaning")
class RobotController(val robotDomainService: RobotDomainService) {

    @PostMapping("/start", produces = [MediaType.TEXT_PLAIN_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun startCleaning(@RequestBody dataInput: String) {
        robotDomainService.executeRobots(dataInput)
    }
}