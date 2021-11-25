package dev.viniciusarnhold.experiments.aot.app

import org.springframework.fu.kofu.reactiveWebApplication
import org.springframework.fu.kofu.webflux.webFlux
import org.springframework.web.reactive.function.server.router

fun routes() = router {
    GET("/") {
        ok().bodyValue("Hellow!")
    }
    GET("/oups") {
        throw RuntimeException("Expected: route used to showcase what happens when an exception is thrown")
    }
}

val app = reactiveWebApplication {
    beans {
        bean(::routes)
    }

    webFlux {
        codecs {
            jackson()
        }
    }
}

fun main(args: Array<String>) {
    app.run(args)
}