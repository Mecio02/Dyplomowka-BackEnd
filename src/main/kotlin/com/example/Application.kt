package com.example

import com.example.plugins.configureFrameworks
import com.example.plugins.configureMonitoring
import com.example.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.plugins.defaultheaders.*


fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    install(DefaultHeaders)

    configureSecurity()
    configureMonitoring()
    configureSerialization()
    configureFrameworks()
    configureRouting()
}
