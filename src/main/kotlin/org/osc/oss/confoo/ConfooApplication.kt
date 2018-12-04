package org.osc.oss.confoo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ConfooApplication

fun main(args: Array<String>) {
    runApplication<ConfooApplication>(*args)
}

