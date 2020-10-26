package org.occidere.ticketseer.service

import org.junit.jupiter.api.Test
import org.occidere.ticketseer.configuration.LineMessengerConfiguration
import org.occidere.ticketseer.enums.SiteType
import org.occidere.ticketseer.util.MessageBuildUtils
import org.occidere.ticketseer.vo.MusicalTicket
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

/**
 * @author occidere
 * @Blog: https://occidere.blog.me
 * @Github: https://github.com/occidere
 * @since 2020-10-26
 */
@SpringBootTest
@ContextConfiguration(classes = [
    LineMessengerConfiguration::class,
    LineMessengerService::class
])
class LineMessengerServiceTest {

    @Autowired
    private var service: LineMessengerService? = null

    @Test
    fun newMusicalMessageSendTest() {
        // BUILD
        val musicalTicket = MusicalTicket(
                id = "1",
                title = "캣츠",
                place = "샤롯데씨어터",
                pageUrl = "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=20007209",
                siteType = SiteType.INTERPARK,
                startDate = "20200909",
                endDate = "20201206"
        )
        val msg = MessageBuildUtils.createNewMusicalMessage(musicalTicket)

        // OPERATE
        service!!.sendPushMessage(msg, true)

        // CHECK
        println(msg)
    }

    @Test
    fun onScreenMusicalMessageSendTest() {
        // BUILD
        val musicals = listOf(
                MusicalTicket(
                        id = "1",
                        title = "캣츠",
                        place = "샤롯데씨어터",
                        pageUrl = "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=20007209",
                        siteType = SiteType.INTERPARK,
                        startDate = "20200909",
                        endDate = "20201206"
                ),
                MusicalTicket(
                        id = "2",
                        title = "맨오브라만차",
                        place = "샤롯데씨어터",
                        pageUrl = "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=20008287",
                        siteType = SiteType.INTERPARK,
                        startDate = "20201218",
                        endDate = "20210301"
                ),
                MusicalTicket(
                        id = "3",
                        title = "노트르담 드 파리",
                        place = "블루스퀘어 인터파크홀",
                        pageUrl = "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GroupCode=20006380",
                        siteType = SiteType.INTERPARK,
                        startDate = "20201110",
                        endDate = "20210117"
                )
        )
        val msg = MessageBuildUtils.createOnScreenMusicalMessage(musicals)

        // OPERATE
        service!!.sendPushMessage(msg)

        // CHECK
        println(msg)
    }
}