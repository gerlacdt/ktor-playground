package com.example

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.gson
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

data class HelloReponse(val greeting: String, val name: String, val age: Int)

fun Application.module() {
    install(DefaultHeaders)
    install(ContentNegotiation) {
        gson { }
    }
    install(CallLogging)
    install(Routing) {
        get("/") {
            call.respond(HttpStatusCode.OK, HelloReponse("hello", "danger", 10))
        }
    }
}

fun main(args: Array<String>) {
    embeddedServer(
        Netty, 8080,
        watchPaths = listOf("com.example"),
        module = Application::module
    ).start()
}
