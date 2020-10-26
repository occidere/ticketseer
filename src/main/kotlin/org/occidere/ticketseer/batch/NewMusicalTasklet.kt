package org.occidere.ticketseer.task

import org.apache.logging.log4j.LogManager
import org.occidere.ticketseer.crawler.InterparkCrawler
import org.occidere.ticketseer.enums.TicketType
import org.occidere.ticketseer.service.LineMessengerService
import org.occidere.ticketseer.service.MusicalTicketRepository
import org.occidere.ticketseer.util.MessageBuildUtils
import org.occidere.ticketseer.vo.Counter
import org.springframework.stereotype.Component
import java.lang.Exception

/**
 * @author occidere
 * @Blog: https://occidere.blog.me
 * @Github: https://github.com/occidere
 * @since 2020-10-23
 */
@Component
class NewMusicalTask(private val musicalTicketRepository: MusicalTicketRepository, private val lineMessengerService: LineMessengerService) : Task {
    private val log = LogManager.getLogger()

    override fun run(args: Array<String>) {
        log.info("Musical Ticket Task!!")

        // 1. Fetch all musicals from Interpark
        val latest = InterparkCrawler.getMusicalTickets()
        log.info("Latest musical count: {}", latest.size)

        // 2. Fetch all musicals from DB
        val prev = musicalTicketRepository.findAllByTicketType(TicketType.MUSICAL)
        log.info("Prev musical count: {}", prev.size)

        // 3. Diff & Find new musicals
        val newMusicals = latest - prev
        log.info("New musical count: {}", newMusicals.size)

        // 4. Send notification
        val result = newMusicals.map { MessageBuildUtils.createNewMusicalMessage(it) }
                .filter { it.isNotBlank() }
                .map {
                    try {
                        lineMessengerService.sendPushMessage(it)
                        Counter(1, 1, 0)
                    } catch (e: Exception) {
                        log.error("Message send failed", e)
                        Counter(1, 0, 1)
                    }
                }.reduce { acc, counter -> acc + counter }
        log.info("Total: {}, Success: {}, Fail: {}", result.total, result.success, result.fail)

        // 5. Save all musicals to DB

        log.info("Musical Ticket Task Finished!!")
    }


}