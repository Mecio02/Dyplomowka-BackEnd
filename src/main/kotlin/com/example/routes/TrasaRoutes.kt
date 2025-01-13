package com.example.routes

import com.example.data.implDAO.TrasaDAOimp
import com.example.data.model.Trasa
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.trasa(trasaDAO: TrasaDAOimp){
    route("/trasy"){
        get{
            call.respond(trasaDAO.getAllTrasy())
        }

        get("/id={id}"){
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Niepoprawne ID")
                return@get
            }
            val trasa = trasaDAO.getTrasyByID(id)
            if (trasa == null) {
                call.respond(HttpStatusCode.NotFound, "Nie znaleziono trasy")
                return@get
            }
            call.respond(trasa)
        }

        get("/ciagnik={idciagnik}"){
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Niepoprawne ID")
                return@get
            }
            val trasa = trasaDAO.getTrasyByCiagnik(id)
            call.respond(trasa)
        }

        get("/naczepa={idnaczepa}"){
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Niepoprawne ID")
                return@get
            }
            val trasa = trasaDAO.getTrasaByNaczepa(id)
            call.respond(trasa)
        }

        get("/kierowca={idkierowca}"){
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Niepoprawne ID")
                return@get
            }
            val trasa = trasaDAO.getTrasyByKierowca(id)
            call.respond(trasa)
        }

        get("/odbiorca={odbiorca}"){
            val odbiorca = call.parameters["odbiorca"]
            if (odbiorca.isNullOrBlank()){
                call.respond(HttpStatusCode.BadRequest, "Wartość nie może być pusta")
                return@get
            }
            val trasa = trasaDAO.getTrasaByOdbiorca(odbiorca)
            call.respond(trasa)
        }

        get("/nadawca={nadawca}"){
            val nadawca = call.parameters["nadawca"]
            if (nadawca.isNullOrBlank()){
                call.respond(HttpStatusCode.BadRequest, "Wartość nie może być pusta")
                return@get
            }
            val trasa = trasaDAO.getTrasaByOdbiorca(nadawca)
            call.respond(trasa)
        }

        post {
            val trasa = call.receive<Trasa>()
            val id = trasaDAO.createTrasa(trasa)
            call.respond(HttpStatusCode.Created, mapOf("id" to id))
        }

        delete("/id={id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Niepoprawne ID")
                return@delete
            }
            val deleted = trasaDAO.deleteTrasa(id)
            if (deleted) {
                call.respond(HttpStatusCode.OK, "Usunięto trase")
            } else {
                call.respond(HttpStatusCode.NotFound, "Nie znaleziono trasy")
            }
        }
    }
}