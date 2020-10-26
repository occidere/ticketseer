package org.occidere.ticketseer.service

import org.occidere.ticketseer.enums.TicketType
import org.occidere.ticketseer.vo.MusicalTicket
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

/**
 * @author occidere
 * @Blog: https://occidere.blog.me
 * @Github: https://github.com/occidere
 * @since 2020-10-23
 */
interface TicketRepository<T> : ElasticsearchRepository<MusicalTicket, String> {
    fun findAllByTicketType(ticketType: TicketType): List<T>

    fun findAllByTicketTypeAndEndDateGreaterThanEqual(ticketType: TicketType, date: String): List<T>
}