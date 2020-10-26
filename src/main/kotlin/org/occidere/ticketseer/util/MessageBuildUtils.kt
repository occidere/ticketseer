package org.occidere.ticketseer.util

import org.occidere.ticketseer.vo.MusicalTicket

/**
 * @author occidere
 * @Blog: https://occidere.blog.me
 * @Github: https://github.com/occidere
 * @since 2020-10-26
 */
object MessageBuildUtils {

    fun createNewMusicalMessage(musical: MusicalTicket) = """[신규 뮤지컬 알림]
- 제목: ${musical.title}
- 장소: ${musical.place}
- 기간: ${musical.startDate} ~ ${musical.endDate}
- 예매: ${musical.pageUrl}
""".trimIndent()

    fun createOnScreenMusicalMessage(musicals: Iterable<MusicalTicket>) = """[공연중 & 예매 가능한 뮤지컬]
${musicals.sortedBy { it.startDate }.joinToString("\n") { "- ${it.title}: ${it.siteType}" }}
""".trimIndent()
}