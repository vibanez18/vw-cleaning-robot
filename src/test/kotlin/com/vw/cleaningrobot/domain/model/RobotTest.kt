package com.vw.cleaningrobot.domain.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class RobotTest {

    @Test
    fun `when moveLeft from North then Direction is West`() {
        val robot = Robot(position = Position(0, 0), direction = Direction.N)

        val robotMovedLeft = robot.moveLeft()

        Assertions.assertThat(robotMovedLeft.direction).isEqualTo(Direction.W)
    }

    @Test
    fun `when moveLeft from West then Direction is South`() {
        val robot = Robot(position = Position(0, 0), direction = Direction.W)

        val robotMovedLeft = robot.moveLeft()

        Assertions.assertThat(robotMovedLeft.direction).isEqualTo(Direction.S)
    }

    @Test
    fun `when moveLeft from South then Direction is East`() {
        val robot = Robot(position = Position(0, 0), direction = Direction.S)

        val robotMovedLeft = robot.moveLeft()

        Assertions.assertThat(robotMovedLeft.direction).isEqualTo(Direction.E)
    }

    @Test
    fun `when moveLeft from East then Direction is North`() {
        val robot = Robot(position = Position(0, 0), direction = Direction.E)

        val robotMovedLeft = robot.moveLeft()

        Assertions.assertThat(robotMovedLeft.direction).isEqualTo(Direction.N)
    }

    @Test
    fun `when moveRight from North then Direction is East`() {
        val robot = Robot(position = Position(0, 0), direction = Direction.N)

        val robotMovedRight = robot.moveRight()

        Assertions.assertThat(robotMovedRight.direction).isEqualTo(Direction.E)
    }

    @Test
    fun `when moveRight from East then Direction is South`() {
        val robot = Robot(position = Position(0, 0), direction = Direction.E)

        val robotMovedRight = robot.moveRight()

        Assertions.assertThat(robotMovedRight.direction).isEqualTo(Direction.S)
    }

    @Test
    fun `when moveRight from South then Direction is West`() {
        val robot = Robot(position = Position(0, 0), direction = Direction.S)

        val robotMovedRight = robot.moveRight()

        Assertions.assertThat(robotMovedRight.direction).isEqualTo(Direction.W)
    }

    @Test
    fun `when moveRight from West then Direction is North`() {
        val robot = Robot(position = Position(0, 0), direction = Direction.W)

        val robotMovedRight = robot.moveRight()

        Assertions.assertThat(robotMovedRight.direction).isEqualTo(Direction.N)
    }

    @Test
    fun `when moveForward from 0 0 N then new position is 0 1 N` () {
        val robot = Robot(position = Position(0, 0), direction = Direction.N)

        val robotMovedForward = robot.moveForward()

        Assertions.assertThat(robotMovedForward.position.x).isEqualTo(0)
        Assertions.assertThat(robotMovedForward.position.y).isEqualTo(1)
        Assertions.assertThat(robotMovedForward.direction).isEqualTo(Direction.N)
    }

    @Test
    fun `when moveForward from 0 0 E then new position is 1 0 E` () {
        val robot = Robot(position = Position(0, 0), direction = Direction.E)

        val robotMovedForward = robot.moveForward()

        Assertions.assertThat(robotMovedForward.position.x).isEqualTo(1)
        Assertions.assertThat(robotMovedForward.position.y).isEqualTo(0)
        Assertions.assertThat(robotMovedForward.direction).isEqualTo(Direction.E)
    }

    @Test
    fun `when moveForward from 0 0 S then new position is 0 0 S` () {
        val robot = Robot(position = Position(0, 0), direction = Direction.S)

        val robotMovedForward = robot.moveForward()

        Assertions.assertThat(robotMovedForward.position.x).isEqualTo(0)
        Assertions.assertThat(robotMovedForward.position.y).isEqualTo(0)
        Assertions.assertThat(robotMovedForward.direction).isEqualTo(Direction.S)
    }

    @Test
    fun `when moveForward from 0 0 W then new position is 0 0 W` () {
        val robot = Robot(position = Position(0, 0), direction = Direction.W)

        val robotMovedForward = robot.moveForward()

        Assertions.assertThat(robotMovedForward.position.x).isEqualTo(0)
        Assertions.assertThat(robotMovedForward.position.y).isEqualTo(0)
        Assertions.assertThat(robotMovedForward.direction).isEqualTo(Direction.W)
    }
}