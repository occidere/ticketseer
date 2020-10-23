package org.occidere.ticketseer.service

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.occidere.ticketseer.configuration.ElasticsearchConfiguration
import org.occidere.ticketseer.enums.SiteType
import org.occidere.ticketseer.enums.TicketType
import org.occidere.ticketseer.vo.MusicalTicket
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories
import org.springframework.test.context.ContextConfiguration

/**
 * @author occidere
 * @Blog: https://occidere.blog.me
 * @Github: https://github.com/occidere
 * @since 2020-10-23
 */
@SpringBootTest
@EnableElasticsearchRepositories
@ContextConfiguration(classes = [
    ElasticsearchConfiguration::class,
    MusicalTicketRepository::class
])
class MusicalTicketRepositoryTest {

    @Autowired
    private val musicalRepository: MusicalTicketRepository? = null

    @Test
    fun findAllByTicketTypeAndSvcYnTest() {
        // BUILD
        println("Hello World")

        // OPERATE
        val musicalTickets = musicalRepository!!.findByTicketTypeAndEndDateGreaterThanEqual(TicketType.MUSICAL)

        // CHECK
        println("musical = $musicalTickets")
        Assertions.assertTrue(musicalTickets.isNotEmpty())
    }

    @Test
    fun saveTest() {
        // BUILD
        val musicalTicket = MusicalTicket(
                id = "20008287",
                title = "뮤지컬 〈맨오브라만차〉",
                place = "샤롯데씨어터",
                pageUrl = "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GroupCode=20008287",
                startDate = "20201218",
                endDate = "20210301",
                siteType = SiteType.INTERPARK,
                imgUrl = "http://ticketimage.interpark.com/Play/image/small/20/20008287.gif"
        )

        // OPERATE
        val saved = musicalRepository!!.save(musicalTicket)

        // CHECK
        val found = musicalRepository.findById(musicalTicket.id).get()
        println("Saved = ${saved.toJsonString()}")
        println("Found = ${found.toJsonString()}")
        Assertions.assertEquals(saved.toJsonString(), found.toJsonString())
    }
}