package com.vw.cleaningrobot.infrastructure.db.entity

import jakarta.persistence.*

@Entity
@Table(name = "robot_cleaning")
data class RobotCleaningEntity(
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    var id: Long? = null,

    @Column(name = "starting_position_x")
    var startingPositionX: Int? = null,

    @Column(name = "starting_position_y")
    var startingPositionY: Int? = null,

    @Column(name = "starting_direction")
    var startingDirection: String? = null,

    @Column(name = "ending_position_x")
    var endingPositionX: Int? = null,

    @Column(name = "ending_position_y")
    var endingPositionY: Int? = null,

    @Column(name = "ending_direction")
    var endingDirection: String? = null
)