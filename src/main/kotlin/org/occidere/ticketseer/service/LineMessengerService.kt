package org.occidere.ticketseer.service

import com.linecorp.bot.client.LineMessagingClient
import com.linecorp.bot.model.PushMessage
import com.linecorp.bot.model.message.TextMessage
import com.linecorp.bot.model.response.BotApiResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

/**
 * @author occidere
 * @Blog: https://occidere.blog.me
 * @Github: https://github.com/occidere
 * @since 2020-10-26
 */
@Service
class LineMessengerService(private val client: LineMessagingClient, @Value("\${line.bot.id}") private val lineBotId: String) {

    fun sendPushMessage(msg: String, notificationDisabled: Boolean = false): BotApiResponse =
            client.pushMessage(PushMessage(lineBotId, TextMessage(msg), notificationDisabled)).get()
}