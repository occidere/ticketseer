package org.occidere.ticketseer

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import kotlin.system.exitProcess

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
class TicketseerApplication

fun main(args: Array<String>) {
    val context = runApplication<TicketseerApplication>(*args)
    val exitCode = SpringApplication.exit(context)
    exitProcess(exitCode)
}
