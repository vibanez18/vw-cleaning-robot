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

    fun moveForward(maxX: Int, maxY: Int): Robot = when (this.direction) {
        Direction.N -> this.copy(position = Position(x = this.position.x, y = this.position.y.plusUntilMax(1, maxY)))
        Direction.W -> this.copy(position = Position(x = this.position.x.minusUnsigned(1), y = this.position.y))
        Direction.S -> this.copy(position = Position(x = this.position.x, y = this.position.y.minusUnsigned(1)))
        Direction.E -> this.copy(position = Position(x = this.position.x.plusUntilMax(1, maxX), y = this.position.y))
    }
}

enum class Direction { N, S, E, W; }

data class Position(val x: Int, val y: Int)

fun Int.minusUnsigned(x: Int): Int = 0.coerceAtLeast(this.minus(x))
fun Int.plusUntilMax(x: Int, max: Int): Int = when {
    this.plus(x) >= max -> max
    else -> this.plus(x)
}