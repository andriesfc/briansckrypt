package com.andriesfc.briansckrypt

import io.micronaut.core.annotation.Blocking
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import java.time.LocalDate
import kotlin.random.Random
import kotlin.random.nextInt

@Controller("/api")
open class Controller {

    @Get( produces = [MediaType.APPLICATION_JSON])
    open fun index(): HttpResponse<Map<String, Any?>> {
        return HttpResponse.ok<Map<String, Any?>?>().body(
            mapOf(
                "one" to 1,
                "two" to mapOf(
                    "name" to "Andries",
                    "date" to LocalDate.of(2023, 4, 29),
                    "rnd" to Random.nextInt(100 .. 100).toString(16),
                    "seq" to (1..10).toList()
                )
            )
        )
    }

    @Post( consumes = [MediaType.APPLICATION_JSON], produces = [MediaType.APPLICATION_JSON])
    @Blocking
    open fun create(@Body contract: Contract): HttpResponse<Contract>? {
        println(contract)
        return HttpResponse.ok(contract)
    }
}

