package org.occidere.ticketseer.configuration

import org.elasticsearch.client.RestHighLevelClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.client.ClientConfiguration
import org.springframework.data.elasticsearch.client.RestClients
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories

/**
 * @author occidere
 * @Blog: https://occidere.blog.me
 * @Github: https://github.com/occidere
 * @since 2020-10-23
 */
@Configuration
@EnableElasticsearchRepositories
class ElasticsearchConfiguration(
        @Value("\${ticketseer.es.endpoint:localhost:9200}")
        private val esEndpoint: String
) : AbstractElasticsearchConfiguration() {
    override fun elasticsearchClient(): RestHighLevelClient = RestClients.create(ClientConfiguration.create(esEndpoint)).rest()
}