package me.kjgleh.springboot.rabbitmq.account

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMqConfig {

    companion object {
        private const val queueName = "accounts"
        private const val topicExchangeName = "accounts-exchange"
    }

    @Bean
    fun queue(): Queue {
        return Queue(queueName, false)
    }

    @Bean
    fun exchange(): TopicExchange {
        return TopicExchange(topicExchangeName)
    }

    @Bean
    fun binding(queue: Queue, exchange: TopicExchange): Binding {
        return BindingBuilder.bind(queue).to(exchange).with("")
    }

    @Bean
    fun rabbitTemplate(
        connectionFactory: ConnectionFactory,
        messageConverter: MessageConverter
    ): RabbitTemplate {
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.messageConverter = messageConverter
        return rabbitTemplate
    }

    @Bean
    fun messageConverter(): MessageConverter {
        return Jackson2JsonMessageConverter()
    }
}