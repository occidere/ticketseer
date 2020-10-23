package org.occidere.ticketseer.crawler

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/**
 * @author occidere
 * @Blog: https://occidere.blog.me
 * @Github: https://github.com/occidere
 * @since 2020-10-23
 */
class InterparkCrawlerTest {

    @Test
    fun getMusicalsTest() {
        // BUILD

        // OPERATE
        val musicalTickets = InterparkCrawler.getMusicalTickets()

        // CHECK
        Assertions.assertTrue(musicalTickets.isNotEmpty())
        println(musicalTickets[0].toJsonString())
    }
}