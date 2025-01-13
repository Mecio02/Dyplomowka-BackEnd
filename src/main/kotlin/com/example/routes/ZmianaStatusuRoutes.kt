package com.example.routes

import com.example.data.implDAO.ZmianaStatusuDAOimp
import com.example.data.model.ZmianyStatusu
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.zmianaStatusu(zmianaStatusuDAO: ZmianaStatusuDAOimp){
    route("/status"){
        get{
           call.respond(zmianaStatusuDAO.getAllZmianyStatusu())
        }

        get("/id={id}"){
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Niepoprawne ID")
                return@get
            }
            val status = zmianaStatusuDAO.getZmianaStatusuByID(id)
            if (status == null) {
                call.respond(HttpStatusCode.NotFound, "Nie znaleziono podanej zmiany statusu")
                return@get
            }
            call.respond(status)
        }

        get("/idTrasy={id}"){
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Niepoprawne ID")
                return@get
            }
            val status = zmianaStatusuDAO.getZmianaStatusuByTrasa(id)
            if (status == null) {
                call.respond(HttpStatusCode.NotFound, "Nie znaleziono podanej zmiany statusu")
                return@get
            }
            call.respond(status)
        }

        get("/status={status}"){
            val status = call.parameters["status"]?.toIntOrNull()
            if (status == null) {
                call.respond(HttpStatusCode.BadRequest, "Niepoprawne ID statusu")
                return@get
            }
            val zmStatusu = zmianaStatusuDAO.getZmianaStatusuByTrasa(status)
            if (zmStatusu == null) {
                call.respond(HttpStatusCode.NotFound, "Nie znaleziono podanej zmiany statusu")
                return@get
            }
            call.respond(zmStatusu)
        }

        get("/koszt={koszt}/{wiedelki}"){
            val koszt = call.parameters["koszt"]?.toBigDecimalOrNull()
            val widelki = call.parameters["widelki"]?.toBigDecimalOrNull()
            if (koszt == null || widelki == null){
                call.respond(HttpStatusCode.BadRequest, "Niepoprawne dane")
                return@get
            }
            val zmStatusu = zmianaStatusuDAO.getZmianaStatusuByKoszt(koszt, widelki)
            call.respond(zmStatusu)
        }

        post {
            val zmianaSt = call.receive<ZmianyStatusu>()
            val id = zmianaStatusuDAO.createStatus(zmianaSt)
            call.respond(HttpStatusCode.Created, mapOf("id" to id))
        }

        delete("/id={id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Niepoprawne ID")
                return@delete
            }
            val deleted = zmianaStatusuDAO.deleteStatus(id)
            if (deleted) {
                call.respond(HttpStatusCode.OK, "Usunięto status")
            } else {
                call.respond(HttpStatusCode.NotFound, "Nie znaleziono statusu")
            }
        }
    }
}