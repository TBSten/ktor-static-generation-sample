package io.github.tbsten

import io.github.tbsten.util.respondCss
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import kotlinx.coroutines.flow.asFlow
import kotlinx.css.*
import kotlinx.html.*
import me.tbsten.ktor.staticGeneration.staticGeneration

fun Application.configureRouting() {
    routing {
        staticGeneration("/") {
            call.respondHtml {
                head {
                    link {
                        rel = "stylesheet"
                        href = "./style.css"
                    }
                }
                body {
                    h1 { +"Hello TBSten blog" }
                }
            }
        }

        staticGeneration(
            "/style.css",
            extension = "",
        ) {
            call.respondCss {
                body {
                    fontSize = 16.px
                }
                h1 {
                    backgroundColor = Color.red
                    color = Color.white
                    fontSize = 32.px
                }
            }
        }

        staticGeneration(
            "/blog/{blogId}",
            staticPaths = { listOf("/blog/1", "/blog/2").asFlow() },
        ) {
            val blogId = call.parameters["blogId"]

            call.respondHtml {
                head {
                    link {
                        rel = "stylesheet"
                        href = "../style.css"
                    }
                }
                body {
                    h1 { +"Blog - $blogId" }
                    p {
                        +"Blog $blogId の内容 ".repeat(100)
                    }
                }
            }
        }
    }
}

fun FlowOrHeadingContent.pageTitle(title: String) {
    h1 {
        +title
    }
}
