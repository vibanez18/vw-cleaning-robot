package com.vw.cleaningrobot

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CleaningRobotApplication

fun main(args: Array<String>) {
	runApplication<CleaningRobotApplication>(*args)
}

fun Any.logger(): Logger = LoggerFactory.getLogger(this::class.java)
