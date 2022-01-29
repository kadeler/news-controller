package com.newscontroller.plugins

import com.newscontroller.routes.responseManager
import io.ktor.server.routing.*
import io.ktor.server.http.content.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    routing {
        responseManager()
        // Static plugin. Try to access `/static/index.html`
        static {
            resources("static")
        }
    }
}
