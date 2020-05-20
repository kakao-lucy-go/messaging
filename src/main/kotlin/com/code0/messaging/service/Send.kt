package com.code0.messaging.service

import com.rabbitmq.client.Channel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class Send {

    @Autowired
    lateinit var connection: Channel

    companion object {
        const val QUEUE_NAME = "hello"
    }

    fun sending(query:String) {
        //connection.queueDeclare(QUEUE_NAME, false, false, false, null)

        connection.basicPublish("", QUEUE_NAME, null, query.toByteArray(charset("UTF-8")))
        println(" [x] Sent '$query'")
    }
}