package org.occidere.ticketseer.batch

import org.apache.logging.log4j.LogManager
import org.occidere.ticketseer.crawler.InterparkCrawler
import org.occidere.ticketseer.service.LineMessengerService
import org.occidere.ticketseer.service.MusicalTicketRepository
import org.occidere.ticketseer.util.MessageBuildUtils
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.stereotype.Component

/**
 * @author occidere
 * @Blog: https://occidere.blog.me
 * @Github: https://github.com/occidere
 * @since 2020-10-23
 */
@StepScope
@Component
class NewMusicalTasklet(private val musicalTicketRepository: MusicalTicketRepository, private val lineMessengerService: LineMessengerService) : Tasklet {
    private val log = LogManager.getLogger()

    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus {
        log.info("New musical ticket task start!")

        // 1. Fetch all musicals from Interpark
        val latest = InterparkCrawler.getMusicalTickets()
        log.info("Latest musical count: {}", latest.size)

        // 2. Fetch all musicals from DB
        val prev = musicalTicketRepository.findAllByTicketType()
        log.info("Prev musical count: {}", prev.size)

        // 3. Find new musicals & Send message
        val processSuccessMusicalTickets = (latest - prev)
                .mapNotNull {
                    try {
                        lineMessengerService.sendPushMessage(MessageBuildUtils.createNewMusicalMessage(it))
                        it
                    } catch (e: Exception) {
                        log.error("Message send failed", e)
                        null
                    }
                }.toList()
        log.info("Success count of new musicals: {}", processSuccessMusicalTickets.size)

        // 5. Save all musicals to DB
        musicalTicketRepository.saveAll(latest)
        log.info("New musical ticket task finished!")

        return RepeatStatus.FINISHED
    }
}