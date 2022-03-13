package me.kjgleh.springboot.rabbitmq.account

import me.kjgleh.springboot.rabbitmq.account.dto.SignedUp
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class AccountEventConsumer {

    @RabbitListener(queues = ["accounts"])
    fun listen(message: SignedUp) {
        println("message: $message")
    }
}