package com.example.routes

import com.example.data.implDAO.KierowcaDAOimp
import com.example.data.model.Kierowca
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.kierowca(kierowcaDAO: KierowcaDAOimp) {
    route("/kierowcy") {
        get {
            call.respond(kierowcaDAO.getAllKierowcy())
        }
        get("/id={id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Niepoprawne ID")
                return@get
            }
            val kierowca = kierowcaDAO.getKierowca(id)
            if (kierowca == null) {
                call.respond(HttpStatusCode.NotFound, "Nie znaleziono kierowcy")
                return@get
            }
            call.respond(kierowca)
        }
        get("/imieNazwisko={imie}/{nazwisko}"){
            val imie = call.parameters["imie"]
            val nazwisko = call.parameters["nazwisko"]
            if (imie == null || nazwisko == null){
                call.respond(HttpStatusCode.BadRequest, "Niepoprawne imie lub nazwisko")
                return@get
            }
            val kierowca = kierowcaDAO.getKierowcaByName(imie, nazwisko)
            call.respond(kierowca)
        }

        post {
            val kierowca = call.receive<Kierowca>()
            val id = kierowcaDAO.createKierowca(kierowca)
            call.respond(HttpStatusCode.Created, mapOf("id" to id))
        }

        delete("/id={id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Niepoprawne ID")
                return@delete
            }
            val deleted = kierowcaDAO.deleteKierowca(id)
            if (deleted) {
                call.respond(HttpStatusCode.OK, "Usunięto kierowcę")
            } else {
                call.respond(HttpStatusCode.NotFound, "Nie znaleziono kierowcy")
            }
        }
    }
}