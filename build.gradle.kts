import me.tbsten.ktor.staticGeneration.KtorStaticGenerationTask

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.ktorStaticGeneration)
}

group = "io.github.tbsten"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.logback.classic)
    implementation(libs.ktor.server.config.yaml)
    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlin.test.junit)

    implementation(libs.ktor.static.generation)
}

val staticGenerate by tasks.getting(KtorStaticGenerationTask::class) {
    mainClass.set("io.github.tbsten.StaticGenerationKt")
    classpath(sourceSets.main.get().runtimeClasspath)
}
