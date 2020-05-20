package com.code0.messaging.service

import com.rabbitmq.client.AMQP
import com.rabbitmq.client.BasicProperties
import com.rabbitmq.client.Channel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

//producer
@Component
class Send {

    @Autowired
    lateinit var connection: Channel

    companion object {
        const val QUEUE_NAME = "hello"
    }

    fun sending(query:String) {
        //connection.queueDeclare(QUEUE_NAME, false, false, false, null)


        var builder = AMQP.BasicProperties.Builder()
        builder.expiration(5000.toString())
        builder.build()
        connection.basicPublish("", QUEUE_NAME, builder.build(), query.toByteArray(charset("UTF-8")))
        println(" [x] Sent '$query'")
    }
}

//주기적으로 무언가를 할 것
//