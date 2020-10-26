package org.occidere.ticketseer.batch

import org.apache.logging.log4j.LogManager
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
 * @since 2020-10-26
 */
@StepScope
@Component
class OnScreenMusicalTasklet(
        private val musicalTicketRepository: MusicalTicketRepository,
        private val lineMessengerService: LineMessengerService) : Tasklet {

    private val log = LogManager.getLogger()

    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus {
        log.info("On screen musical task start!")

        // 1. Fetch all on screen musicals from DB
        val onScreenMusicalTickets = musicalTicketRepository.findAllByTicketTypeAndEndDateGreaterThanEqual()
        log.info("On screen musical count: {}", onScreenMusicalTickets.size)

        // 2. Send notification
        lineMessengerService.sendPushMessage(MessageBuildUtils.createOnScreenMusicalMessage(onScreenMusicalTickets))
        log.info("On screen musical task finished!")

        return RepeatStatus.FINISHED
    }
}