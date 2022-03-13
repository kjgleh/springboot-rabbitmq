package me.kjgleh.springboot.rabbitmq.account

import me.kjgleh.springboot.rabbitmq.account.dto.SignedUp
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/accounts/event")
class AccountEventProducer(
    private val rabbitTemplate: RabbitTemplate,
) {

    companion object {
        private const val topicExchange = "accounts-exchange"
    }

    @PostMapping("/signedUp")
    fun sendMessage(@RequestBody message: SignedUp) {
        println("Sending message...")
        rabbitTemplate.convertAndSend(
            topicExchange,
            "",
            message)
    }
}