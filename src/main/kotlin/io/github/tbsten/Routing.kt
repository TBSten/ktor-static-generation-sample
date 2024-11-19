package io.github.tbsten

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.flow.asFlow
import me.tbsten.ktor.staticGeneration.staticGeneration

fun Application.configureRouting() {
    routing {
        staticGeneration("/") {
            call.respondText("Hello World!")
        }

        staticGeneration(
            "/blog/{blogId}",
            staticPaths = { listOf("/blog/1", "/blog/2").asFlow() },
        ) {
            val blogId = call.parameters["blogId"]
            call.respondText("Hello blog id=$blogId")
        }
    }
}
