package org.occidere.ticketseer.util

import com.fasterxml.jackson.databind.ObjectMapper

/**
 * @author occidere
 * @Blog: https://occidere.blog.me
 * @Github: https://github.com/occidere
 * @since 2020-10-23
 */
object JacksonUtils {
    private val mapper = ObjectMapper()

    fun writeValueAsString(obj: Any): String = mapper.writeValueAsString(obj)
}