package org.occidere.ticketseer.task

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.occidere.ticketseer.TicketseerApplication
import org.occidere.ticketseer.configuration.ElasticsearchConfiguration
import org.occidere.ticketseer.enums.SiteType
import org.occidere.ticketseer.service.MusicalTicketRepository
import org.occidere.ticketseer.vo.MusicalTicket
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories
import org.springframework.test.context.ContextConfiguration

/**
 * @author occidere
 * @Blog: https://occidere.blog.me
 * @Github: https://github.com/occidere
 * @since 2020-10-26
 */
@SpringBootTest
@EnableElasticsearchRepositories
@ContextConfiguration(classes = [
    ElasticsearchConfiguration::class,
    MusicalTicketRepository::class
])
class MusicalTaskTest {

    @Test
    fun musicalDiffTest() {
        // BUILD
        val latest = listOf(
                MusicalTicket(
                        id = "1",
                        title = "캣츠",
                        place = "샤롯데씨어터",
                        pageUrl = "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=20007209",
                        siteType = SiteType.INTERPARK,
                        startDate = "2020-09-09",
                        endDate = "2020-12-06"
                ),
                MusicalTicket(
                        id = "3",
                        title = "노트르담 드 파리",
                        place = "블루스퀘어 인터파크홀",
                        pageUrl = "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GroupCode=20006380",
                        siteType = SiteType.INTERPARK,
                        startDate = "2020-11-10",
                        endDate = "2021-01-17"
                )
        )
        val prev = listOf(
                MusicalTicket(
                        id = "1",
                        title = "캣츠",
                        place = "샤롯데씨어터",
                        pageUrl = "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=20007209",
                        siteType = SiteType.INTERPARK,
                        startDate = "2020-09-09",
                        endDate = "2020-12-06"
                ),
                MusicalTicket(
                        id = "2",
                        title = "맨오브라만차",
                        place = "샤롯데씨어터",
                        pageUrl = "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=20008287",
                        siteType = SiteType.INTERPARK,
                        startDate = "2020-12-18",
                        endDate = "2021-03-01"
                )
        )

        // OPERATE
        val newMusicals = latest - prev
        val deletedMusicals = prev - latest
        val notChangedMusicals = prev intersect latest

        // CHECK
        println("[NEW]\n$newMusicals\n")
        println("[DELETED]\n$deletedMusicals\n")
        println("[NOT CHANGED]\n$notChangedMusicals\n")
        Assertions.assertEquals(1, newMusicals.size)
        Assertions.assertEquals(1, deletedMusicals.size)
        Assertions.assertEquals(1, notChangedMusicals.size)
    }
}