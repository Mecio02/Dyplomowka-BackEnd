package com.example.routes

import com.example.data.implDAO.CiagnikDAOimp
import com.example.data.model.Ciagnik
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.ciagnik(ciagnikDAO: CiagnikDAOimp){
    route("ciagniki"){

        //wyyoluje funkcje zwracajaca wszyskie pojazdy
        get {
            call.respond(ciagnikDAO.getAllCiagniki())
        }

        //wywoluje funkcje ktora zwraca wszystkie pojazdy po numerze ID
        get("/id={id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Niepoprawne ID")
                return@get
            }
            val ciagnik = ciagnikDAO.getCiagnikbyID(id)
            if (ciagnik == null) {
                call.respond(HttpStatusCode.NotFound, "Nie znaleziono pojazdu")
                return@get
            }
            call.respond(ciagnik)
        }

        //wywoluje funkcje ktora zwraca wszystkie pojazdy gdzie dopuszczalna masa calkowita jest rowna badz wieksza
        get("/dmc={dmc}"){
            val dmc = call.parameters["dmc"]?.toIntOrNull()
            if (dmc == null){
                call.respond(HttpStatusCode.BadRequest, "Niepoprawne dane")
                return@get
            }
            val ciagnik = ciagnikDAO.getCiagnikiByDMC(dmc)
            call.respond(ciagnik)
        }

        //wywoluje funkcje ktora zwraca wszystkie pojazdy po nazwie marki
        get("/marka={marka}"){
            val marka = call.parameters["marka"]
            if (marka.isNullOrBlank()){
                call.respond(HttpStatusCode.BadRequest, "Wartość nie może być pusta")
                return@get
            }
            val ciagnik = ciagnikDAO.getCiagnikiByMarka(marka)
            call.respond(ciagnik)
        }

        //wywoluje funkcje ktora zwraca wszystkie pojazdy po numerze tablicy rejestracynej
        get("/nrTablicy={nrTablicy}"){
            val nrTablicy = call.parameters["nrTablicy"]
            if (nrTablicy.isNullOrBlank()){
                call.respond(HttpStatusCode.BadRequest, "Wartość nie może być pusta")
                return@get
            }
            val ciagnik = ciagnikDAO.getCiagnikiByTablicaRejestracyjna(nrTablicy)
            call.respond(ciagnik)
        }

        post {
            val ciagnik = call.receive<Ciagnik>()
            val id = ciagnikDAO.createCiagnik(ciagnik)
            call.respond(HttpStatusCode.Created, mapOf("id" to id))
        }

        delete("/id={id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Niepoprawne ID")
                return@delete
            }
            val deleted = ciagnikDAO.deleteCiagnik(id)
            if (deleted) {
                call.respond(HttpStatusCode.OK, "Usunięto ciągnik")
            } else {
                call.respond(HttpStatusCode.NotFound, "Nie znaleziono ciagnika")
            }
        }
    }
}