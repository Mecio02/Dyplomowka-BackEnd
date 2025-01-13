package com.example.routes

import com.example.data.implDAO.NaczepaDAOimp
import com.example.data.model.Naczepa
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.naczepa(naczepaDAO: NaczepaDAOimp) {
    route("/naczepy") {

        //wyyoluje funkcje zwracajaca wszyskie pojazdy
        get {
            call.respond(naczepaDAO.getAllNaczepy())
        }

        //wywoluje funkcje ktora zwraca wszystkie pojazdy po numerze ID
        get("/id={id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Niepoprawne ID")
                return@get
            }
            val naczepa = naczepaDAO.getNaczepyByID(id)
            if (naczepa == null) {
                call.respond(HttpStatusCode.NotFound, "Nie znaleziono pojazdu")
                return@get
            }
            call.respond(naczepa)
        }

        //wywoluje funkcje ktora zwraca wszystkie pojazdy po numerze tablicy rejestracynej
        get("/nrTablicy={nrTablicy}"){
            val nrTablicy = call.parameters["nrTablicy"]
            if (nrTablicy.isNullOrBlank()){
                call.respond(HttpStatusCode.BadRequest, "Wartość nie może być pusta")
                return@get
            }
            val naczepa = naczepaDAO.getNaczepyByNrTablicyRejestracynej(nrTablicy)
            call.respond(naczepa)
        }

        post {
            val naczepa = call.receive<Naczepa>()
            val id = naczepaDAO.createNaczepa(naczepa)
            call.respond(HttpStatusCode.Created, mapOf("id" to id))
        }

        delete("/id={id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Niepoprawne ID")
                return@delete
            }
            val deleted = naczepaDAO.deleteNaczepa(id)
            if (deleted) {
                call.respond(HttpStatusCode.OK, "Usunięto naczepe")
            } else {
                call.respond(HttpStatusCode.NotFound, "Nie znaleziono naczepe")
            }
        }
    }
}