package com.example

import com.example.data.implDAO.*
import com.example.routes.kierowca
import com.example.routes.ciagnik
import com.example.routes.naczepa
import com.example.routes.trasa
import com.example.routes.zmianaStatusu
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause" , status = HttpStatusCode.InternalServerError)
        }
    }
    DbFactory.init(environment)
    val kierowcaDao = KierowcaDAOimp()
    val ciagnikDao = CiagnikDAOimp()
    val naczepaDao = NaczepaDAOimp()
    val trasaDao = TrasaDAOimp()
    val zmianaStatusuDao = ZmianaStatusuDAOimp()

    routing  {
        kierowca(kierowcaDao)
        ciagnik(ciagnikDao)
        naczepa(naczepaDao)
        trasa(trasaDao)
        zmianaStatusu(zmianaStatusuDao)
    }
}
