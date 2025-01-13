package com.example.data.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.date


//Ten objekt jest reprezentacja danych w bazie danych (dziala na podobnej zasadzie co interfejs)
object TrasaTable: IntIdTable(){
    val idCiagnika = integer("id_ciagnika")
        .uniqueIndex()
        .references(CiagnikTable.id)
    val idKierowcy = integer("id_kierowcy")
        .uniqueIndex()
        .references(KierowcaTable.id)
    val idNaczepy = integer("id_naczepy")
        .uniqueIndex()
        .references(NaczepaTable.id)
    val lokalizacjaKontrahentOdbiorcy = varchar("lokalizacja_kontrahent_odbiorcy",255)
    val kontrahentOdbiorca = varchar("kontrahent_odbiorca", 255)
    val miejsceOdbioruPojazdu = varchar("miejsce_odbioru_pojazdu", 255)
    val dataOdbioruPojazdu = date("data_odbioru_pojazdu")
    val odczytLicznikaKmWyjazd = integer("odczyt_licznika_km_wyjazd")
    val odczytLicznikaKmDojazd = integer("odczyt_licznika_km_dojazd")
    val opis = varchar("opis",255)
    val kontrahentNadawca = varchar("kontrahent_nadawca", 255)
    val lokalizacjaKontrahentaNadawcy = varchar("lokalizacja_kontrahenta_nadawcy", 255)
}

//Ta klasa danych reprezentuje dane w aplikacji oraz pozwala na ich serializacje
@Serializable
data class Trasa(
    val id: Int,
    val idCiagnika: Int,
    val idKierowcy: Int,
    val idNaczepy: Int,
    val lokalizacjaKontrahentOdbiorcy: String,
    val kontrahentOdbiorca: String,
    val miejsceOdbioruPojazdu: String,
    val dataOdbioruPojazdu: LocalDate,
    val odczytLicznikaKmWyjazd: Int,
    val odczytLicznikaKmDojazd: Int,
    val opis: String,
    val kontrahentNadawca: String,
    val lokalizacjaKontrahentaNadawcy: String
)