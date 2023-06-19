package com.vw.cleaningrobot.infrastructure.db.repository

import com.vw.cleaningrobot.infrastructure.db.entity.RobotCleaningEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RobotCleaningJpaRepository: JpaRepository <RobotCleaningEntity, Long>