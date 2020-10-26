package org.occidere.ticketseer.configuration

import org.occidere.ticketseer.batch.NewMusicalTasklet
import org.occidere.ticketseer.batch.OnScreenMusicalTasklet
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

/**
 * @author occidere
 * @Blog: https://occidere.blog.me
 * @Github: https://github.com/occidere
 * @since 2020-10-26
 */
@Configuration
@EnableBatchProcessing
@ComponentScan(basePackages = ["org.occidere.ticketseer"])
class MusicalJobConfiguration(
        private val jobBuilderFactory: JobBuilderFactory,
        private val stepBuilderFactory: StepBuilderFactory,
        private val newMusicalTasklet: NewMusicalTasklet,
        private val onScreenMusicalTasklet: OnScreenMusicalTasklet
) {

    @Bean
    fun newMusicalJob() = jobBuilderFactory.get("newMusicalJob")
            .incrementer(RunIdIncrementer())
            .start(newMusicalStep())
            .build()

    @Bean
    @JobScope
    fun newMusicalStep() = stepBuilderFactory.get("newMusicalStep")
            .tasklet(newMusicalTasklet)
            .build()

    @Bean
    fun onScreenMusicalJob() = jobBuilderFactory.get("onScreenMusicalJob")
            .incrementer(RunIdIncrementer())
            .start(onScreenMusicalStep())
            .build()
    
    @Bean
    @JobScope
    fun onScreenMusicalStep() = stepBuilderFactory.get("onScreenMusicalStep")
            .tasklet(onScreenMusicalTasklet)
            .build()
}