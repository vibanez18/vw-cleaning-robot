package com.vw.cleaningrobot.domain.model

data class Robot(
    val id: Long? = null,
    val position: Position,
    val direction: Direction,
) {
    fun moveLeft(): Robot = when (this.direction) {
        Direction.N -> this.copy(direction = Direction.W)
        Direction.W -> this.copy(direction = Direction.S)
        Direction.S -> this.copy(direction = Direction.E)
        Direction.E -> this.copy(direction = Direction.N)
    }

    fun moveRight(): Robot = when (this.direction) {
        Direction.N -> this.copy(direction = Direction.E)
        Direction.E -> this.copy(direction = Direction.S)
        Direction.S -> this.copy(direction = Direction.W)
        Direction.W -> this.copy(direction = Direction.N)
    }

    fun moveForward(): Robot = when(this.direction) {
        Direction.N -> this.copy(position = Position(x = this.position.x, y = this.position.y.plus(1)))
        Direction.W -> this.copy(position = Position(x = this.position.x.minus(1) , y = this.position.y))
        Direction.S -> this.copy(position = Position(x = this.position.x, y = this.position.y.minus(1)))
        Direction.E -> this.copy(position = Position(x = this.position.x.plus(1), y = this.position.y))
    }

}

enum class Direction { N, S, E, W; }

data class Position(
    val x: Int,
    val y: Int
)