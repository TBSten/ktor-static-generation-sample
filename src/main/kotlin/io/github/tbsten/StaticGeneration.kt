package io.github.tbsten

import kotlinx.coroutines.runBlocking
import me.tbsten.ktor.staticGeneration.generateStatic

fun main() {
    runBlocking {
        generateStatic {
            configureRouting()
        }
    }
}
