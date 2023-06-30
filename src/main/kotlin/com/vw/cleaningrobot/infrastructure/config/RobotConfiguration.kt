package com.vw.cleaningrobot.infrastructure.config

import com.vw.cleaningrobot.application.RobotHandler
import com.vw.cleaningrobot.domain.service.RobotDomainService
import com.vw.cleaningrobot.infrastructure.db.repository.RobotCleaningJpaRepository
import com.vw.cleaningrobot.infrastructure.db.repository.RobotCleaningRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RobotConfiguration {

    @Bean
    fun robotCleaningRepository(robotCleaningJpaRepository: RobotCleaningJpaRepository): RobotCleaningRepository =
        RobotCleaningRepository(robotCleaningJpaRepository)

    @Bean
    fun robotDomainService(robotCleaningRepository: RobotCleaningRepository): RobotDomainService =
        RobotDomainService(robotCleaningRepository)

    @Bean
    fun robotHandler(robotDomainService: RobotDomainService): RobotHandler = RobotHandler(robotDomainService)

}