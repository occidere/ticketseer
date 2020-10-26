package org.occidere.ticketseer.crawler

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

/**
 * @author occidere
 * @Blog: https://occidere.blog.me
 * @Github: https://github.com/occidere
 * @since 2020-10-26
 */
open class Crawler {
    protected fun getDocument(url: String): Document = Jsoup.connect(url)
            .userAgent("Mozilla/5.0")
            .followRedirects(true)
            .get()
}