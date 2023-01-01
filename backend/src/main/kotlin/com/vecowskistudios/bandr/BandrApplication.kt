package com.vecowskistudios.bandr

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BandrApplication

fun main(args: Array<String>) {
    runApplication<BandrApplication>(*args)
}
