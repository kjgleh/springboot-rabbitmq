package me.kjgleh.springboot.rabbitmq.account.dto

import me.kjgleh.springboot.rabbitmq.annotation.NoArg

@NoArg
data class SignedUp(
    val id: Long,
    val name: String,
)
