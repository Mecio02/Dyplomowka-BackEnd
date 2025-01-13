package com.example.data.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.date

//Ten objekt jest reprezentacja danych w bazie danych (dziala na podobnej zasadzie co interfejs)
object CiagnikTable : IntIdTable(){
    val nrTablicyRejestracyjnej = varchar("nr_tablicy_rejestracyjnej", 10)
    val marka = varchar("marka", 50)
    val model = varchar("model", 80)
    val iloscOsi = integer("ilosc_osi")
    val dopuszczalnaMasaCalkowita = integer("dopuszczalna_masa_calkowita")
    val masaPojazdu = integer("masa_pojazdu")
    val dataWaznosciPrzegladu = date("data_waznosci_przegladu")
    val rodzajOpon = varchar("rodzaj_opon", 50)
    val typSkrzyni = varchar("typ_skrzyni", 80)
    val vin = varchar("vin", 17)
    val zasiegMin = integer("zasieg_min")
    val zasiegMax = integer("zasieg_max")
    val dataZakupuPojazdu = date("data_zakupu_pojazdu")
    val typPojazdu = varchar("typ_pojazdu", 50)
    val stanPojazdu = varchar("stan_pojazdu", 50)
    val opis = varchar("opis", 255)
    val normaEmisji = varchar("norma_emisji_spalin", 50)
    val kolor = varchar("kolor", 30)
    val liczbaMiejsc = integer("liczba_miejsc")
    val wlasciciel = varchar("wlasciciel", 80)
    val dataGwarancji = date("data_gwarancji")
    val rodzajSilnika = varchar("rodzaj_silnika", 50)
    val rodzajPaliwa = varchar("rodzaj_paliwa", 30)
    val rocznyLimitKm = integer("roczny_limit_km")
    val dataPierwszejRejestracji = date("data_pierwszej_rejestracji")
    val nrKartyPaliwowej = integer("nr_karty_paliwowej")

}

//Ta klasa danych reprezentuje dane w aplikacji oraz pozwala na ich serializacje
@Serializable
data class Ciagnik(
    val id: Int,
    val nrTablicyRejestracyjnej: String,
    val marka: String,
    val model: String,
    val iloscOsi: Int,
    val dopuszczalnaMasaCalkowita: Int,
    val masaPojazdu: Int,
    val dataWaznosciPrzegladu: LocalDate,
    val rodzajOpon: String,
    val typSkrzyni: String,
    val vin: String,
    val zasiegMin: Int,
    val zasiegMax: Int,
    val dataZakupuPojazdu: LocalDate,
    val typPojazdu: String,
    val stanPojazdu: String,
    val opis: String,
    val normaEmisji: String,
    val kolor: String,
    val liczbaMiejsc: Int,
    val wlasciciel: String,
    val dataGwarancji: LocalDate,
    val rodzajSilnika: String,
    val rodzajPaliwa: String,
    val rocznyLimitKm: Int,
    val dataPierwszejRejestracji: LocalDate,
    val nrKartyPaliwowej: Int
)

