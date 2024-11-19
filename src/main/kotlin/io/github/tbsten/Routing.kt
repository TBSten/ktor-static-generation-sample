package io.github.tbsten

import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.flow.asFlow
import kotlinx.html.FlowOrHeadingContent
import kotlinx.html.body
import kotlinx.html.h1
import me.tbsten.ktor.staticGeneration.staticGeneration

fun Application.configureRouting() {
    routing {
        staticGeneration("/") {
            call.respondHtml {
                body {
                    h1 { +"Hello TBSten blog" }
                }
            }
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

fun FlowOrHeadingContent.pageTitle(title: String) {
    h1 {
        +title
    }
}
