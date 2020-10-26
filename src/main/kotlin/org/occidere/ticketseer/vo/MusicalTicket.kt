package org.occidere.ticketseer.vo

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.occidere.ticketseer.enums.SiteType
import org.occidere.ticketseer.enums.TicketType
import org.occidere.ticketseer.util.JacksonUtils
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document

/**
 * @author occidere
 * @Blog: https://occidere.blog.me
 * @Github: https://github.com/occidere
 * @since 2020-10-23
 */
@Document(
        indexName = "musical-ticket",
        createIndex = true,
        replicas = 1,
        shards = 5,
        refreshInterval = "30s"
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class MusicalTicket(
        @Id val id: String,
        val title: String,
        val place: String,
        val pageUrl: String,
        val startDate: String,
        val endDate: String,
        val siteType: SiteType
) {
    val ticketType: TicketType = TicketType.MUSICAL

    fun toJsonString(): String = JacksonUtils.writeValueAsString(this)
}