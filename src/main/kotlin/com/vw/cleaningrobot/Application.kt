package com.vw.cleaningrobot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CleaningRobotApplication

fun main(args: Array<String>) {
	runApplication<CleaningRobotApplication>(*args)
}
