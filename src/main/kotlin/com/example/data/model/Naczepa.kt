package com.example.data.model


import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.date

//Ten objekt jest reprezentacja danych w bazie danych (dziala na podobnej zasadzie co interfejs)
object NaczepaTable: IntIdTable(){
    val nrTablicyRejestracyjnej = varchar("nr_tablicy_rejestracyjnej", 10)
    val masaWlasna = integer("masa_wlasna")
    val dopuszczalnaMasaCalkowita = integer("dopuszczalna_masa_calkowita")
    val rodzajOpon = varchar("rodzaj_opon", 50)
    val typ = varchar("typ", 80)
    val iloscOsi = integer("ilosc_osi")
    val dataWaznosciPrzegladu = date("data_waznosci_przegladu")
    val vin = varchar("vin", 17)
    val dataZakupu = date("data_zakupu")
    val dataPierwszejRejestracji = date("data_pierwszej_rejestracji")
}

//Ta klasa danych reprezentuje dane w aplikacji oraz pozwala na ich serializacje
@Serializable
data class Naczepa(
    val id: Int,
    val nrTablicyRejestracyjnej: String,
    val masaWlasna: Int,
    val dopuszczalnaMasaCalkowita: Int,
    val rodzajOpon: String,
    val typ: String,
    val iloscOsi: Int,
    val dataWaznosciPrzegladu: LocalDate,
    val vin: String,
    val dataZakupu: LocalDate,
    val dataPierwszejRejestracji: LocalDate
)
