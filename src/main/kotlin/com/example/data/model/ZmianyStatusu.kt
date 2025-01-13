package com.example.data.model

import com.example.utils.TwoDecimalPlacesSerializer
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import java.math.BigDecimal


//Ten objekt jest reprezentacja danych w bazie danych (dziala na podobnej zasadzie co interfejs)
object ZmianyStatusuTable: IntIdTable(){
    val idTrasy = integer("id_trasy")
        .uniqueIndex()
        .references(TrasaTable.id)
    val status = integer("status") //gdzie wyniki:
    // 0 => Nie rozpoczęto
    // 1 => W trakcie
    // 2 => Ukończono
    // 3 => Anulowano
    // 4 => Wypadek
    // 5 => Awaria
    // 6 => Inne
    // 7 => Ładowanie towaru
    // 8 => Wyjazd
    // 9 => Rozładowanie pojazdu
    // 10 => Postój

    val dataZmianaStatusu = datetime("data_zmiany_statusu")
    val lokalizacja = varchar("lokalizacja", 255)
    val dokladnyOpis = varchar("dokladny_opis", 255)
    val koszt = decimal("koszt", 8, 2)

}

//Ta klasa danych reprezentuje dane w aplikacji oraz pozwala na ich serializacje
@Serializable
data class ZmianyStatusu(
    val id: Int,
    val idTrasy: Int,
    val status: Int,
    val dataZmianaStatusu: LocalDateTime,
    val lokalizacja: String,
    val dokladnyOpis: String,
    @Serializable(with = TwoDecimalPlacesSerializer ::class)
    val koszt: BigDecimal,
)

