package com.vecowskistudios.pitchly

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PitchlyApplication

fun main(args: Array<String>) {
    runApplication<PitchlyApplication>(*args)
}
