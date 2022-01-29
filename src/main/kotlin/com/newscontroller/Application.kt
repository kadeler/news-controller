package com.newscontroller

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.newscontroller.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureSerialization()
        configureTemplating()
        configureMonitoring()
        configureHTTP()
    }.start(wait = true)
}
