package com.code0.messaging.controller

import com.code0.messaging.service.Send
import com.rabbitmq.client.Channel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller("/")
class Controller {

    @Autowired
    lateinit var connection : Channel

    @Autowired
    lateinit var send:Send

    @GetMapping("command")
    fun send(@RequestParam("query") query:String) : String{

        send.sending(query)
        return query

    }
}