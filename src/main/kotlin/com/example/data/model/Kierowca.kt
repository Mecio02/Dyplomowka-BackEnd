package com.example.data.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.date

//Ten objekt jest reprezentacja danych w bazie danych (dziala na podobnej zasadzie co interfejs)
object KierowcaTable : IntIdTable(){
    val imie = varchar("imie", 100)
    val nazwisko = varchar("nazwisko", 100)
    val dataUrodzenia = date("data_urodzenia")
    val dataWaznosciPrawaJazdy = date("data_waznosci_prawa_jazdy")
    val dataZatrudnienia = date("data_zatrudnienia")
    val pesel = varchar("pesel", 11)
    val nrTelefonu = varchar("nr_telefonu", 15)
}


//Ta klasa danych reprezentuje dane w aplikacji oraz pozwala na ich serializacje
@Serializable
data class Kierowca(
    val id: Int,
    val imie: String,
    val nazwisko: String,
    val dataUrodzenia: LocalDate,
    val dataWaznosciPrawaJazdy: LocalDate,
    val dataZatrudnienia: LocalDate,
    val pesel: String,
    val nrTelefonu: String
)


