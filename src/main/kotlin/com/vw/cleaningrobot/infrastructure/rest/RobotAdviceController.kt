package com.vw.cleaningrobot.infrastructure.rest

import com.vw.cleaningrobot.domain.service.RobotDomainServiceException
import com.vw.cleaningrobot.logger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class RobotAdviceController {

    @ExceptionHandler(RobotDomainServiceException::class)
    fun handleRobotDomainServiceException(exception: RobotDomainServiceException): ResponseEntity<Any> {
        logger().error(exception.message)

        return ResponseEntity(HttpStatus.BAD_REQUEST)
    }
}