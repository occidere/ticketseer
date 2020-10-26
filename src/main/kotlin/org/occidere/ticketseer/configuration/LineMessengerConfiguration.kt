package org.occidere.ticketseer.configuration

import com.linecorp.bot.client.LineMessagingClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @author occidere
 * @Blog: https://occidere.blog.me
 * @Github: https://github.com/occidere
 * @since 2020-10-26
 */
@Configuration
class LineMessengerConfiguration {
    @Bean
    fun lineMessagingClient(@Value("\${line.channel.token}") lineChannelToken: String? = null): LineMessagingClient = LineMessagingClient.builder(lineChannelToken).build()
}