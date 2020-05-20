package com.code0.messaging.config

import com.code0.messaging.service.Receive
import com.rabbitmq.client.Channel
import com.rabbitmq.client.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Config {

    @Bean
    fun connection(): Channel? {
        val factory = ConnectionFactory()
        factory.host = "localhost"
        factory.port = 5672
        val connection = factory.newConnection()
        val channel = connection.createChannel()

        channel.queueDeclare(Receive.QUEUE_NAME, false, false, false, null)
        return channel
    }
}