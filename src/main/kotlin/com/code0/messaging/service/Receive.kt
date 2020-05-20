package com.code0.messaging.service

import com.rabbitmq.client.AMQP
import com.rabbitmq.client.Channel
import com.rabbitmq.client.DefaultConsumer
import com.rabbitmq.client.Envelope
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class Receive {
    @Autowired
    lateinit var connection: Channel

    companion object {
        const val QUEUE_NAME = "hello"
    }

    @RabbitListener(queues = arrayOf(QUEUE_NAME), concurrency = 3.toString())
    fun receiving() {
        //connection.queueDeclare(QUEUE_NAME, false, false, false, null)
        println(" [*] Waiting for messages. To exit press CTRL+C")

        val consumer = object : DefaultConsumer(connection) {
            override fun handleDelivery(consumerTag: String, envelope: Envelope, properties: AMQP.BasicProperties, body: ByteArray) {
                val message = String(body, charset("UTF-8"))
                println(" [x] Received '$message'")
            }
        }
        connection.basicConsume(QUEUE_NAME, true, consumer)

    }
}