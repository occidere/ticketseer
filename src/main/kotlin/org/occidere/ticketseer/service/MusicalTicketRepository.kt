package org.occidere.ticketseer.service

import org.occidere.ticketseer.enums.TicketType
import org.occidere.ticketseer.vo.MusicalTicket
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * @author occidere
 * @Blog: https://occidere.blog.me
 * @Github: https://github.com/occidere
 * @since 2020-10-23
 */
interface MusicalTicketRepository : ElasticsearchRepository<MusicalTicket, String> {
    fun findByTicketTypeAndEndDateGreaterThanEqual(ticketType: TicketType, now: String = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)): List<MusicalTicket>
}