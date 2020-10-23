package org.occidere.ticketseer

import org.occidere.ticketseer.service.MusicalTicketRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TicketseerApplication : ApplicationRunner {

    @Autowired
    private val musicalTicketRepository: MusicalTicketRepository? = null

    override fun run(args: ApplicationArguments?) {
        // TODO: Implements business logic here

        // 1. args 로 어떤 task 를 할 것인지 입력받은것 파싱

        // 2. 지정한 task 수행 or 없으면 에러 로깅
    }
}

fun main(args: Array<String>) {
    runApplication<TicketseerApplication>(*args)
}
