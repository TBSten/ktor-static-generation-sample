package io.github.tbsten

import io.ktor.server.application.*
import me.tbsten.ktor.staticGeneration.StaticGeneration

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    install(StaticGeneration)
    configureRouting()
}
