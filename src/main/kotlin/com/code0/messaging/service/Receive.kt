package com.code0.messaging.service

import com.rabbitmq.client.AMQP
import com.rabbitmq.client.Channel
import com.rabbitmq.client.DefaultConsumer
import com.rabbitmq.client.Envelope
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.IOException

//consumer
@Component
class Receive {
    @Autowired
    lateinit var connection: Channel

    companion object {
        const val QUEUE_NAME = "hello"
    }

    //, concurrency = 3.toString()
    @RabbitListener(queues = arrayOf(QUEUE_NAME))
    fun receiving(string: String) {
        //connection.queueDeclare(QUEUE_NAME, false, false, false, null)
        //println(" [*] Waiting for messages. To exit press CTRL+C")
        println(string)
//
//        val consumer = object : DefaultConsumer(connection) {
//            @Throws(IOException::class)
//            override fun handleDelivery(consumerTag: String, envelope: Envelope, properties: AMQP.BasicProperties, body: ByteArray) {
//                val message = String(body, charset("UTF-8"))
//                println(" [x] Received '$message'")
//                connection.ack
//            }
//        }
//        connection.basicConsume(QUEUE_NAME, true, consumer)

    }
}