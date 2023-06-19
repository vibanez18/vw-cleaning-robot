package com.vw.cleaningrobot.domain.model

data class RobotCleaning(
    val id: Long? = null,
    val startingPosition: Position? = null,
    val startingDirection: Direction? = null,
    val endingPosition: Position? = null,
    val endingDirection: Direction? = null
)