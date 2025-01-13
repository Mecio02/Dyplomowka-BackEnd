package com.example.data.implDAO

import com.example.data.interfaceDAO.KierowcaDAO
import com.example.data.model.Kierowca
import com.example.data.model.KierowcaTable
import com.example.query
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toKotlinLocalDate
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class KierowcaDAOimp : KierowcaDAO {

    private fun ResultRow.toKierowca() = Kierowca(
        id = this[KierowcaTable.id].value,
        imie = this[KierowcaTable.imie],
        nazwisko = this[KierowcaTable.nazwisko],
        dataUrodzenia = this[KierowcaTable.dataUrodzenia].toKotlinLocalDate(),
        dataWaznosciPrawaJazdy = this[KierowcaTable.dataWaznosciPrawaJazdy].toKotlinLocalDate(),
        dataZatrudnienia = this[KierowcaTable.dataZatrudnienia].toKotlinLocalDate(),
        pesel = this[KierowcaTable.pesel],
        nrTelefonu = this[KierowcaTable.nrTelefonu]
    )
    override suspend fun getAllKierowcy(): List<Kierowca> = query{
        KierowcaTable.selectAll().map { it.toKierowca() }
    }

    override suspend fun getKierowca(id: Int):Kierowca? = query {
        KierowcaTable
            .selectAll().where(KierowcaTable.id eq id)
            .map { it.toKierowca() }
            .singleOrNull()
    }

    override suspend fun getKierowcaByName(imie: String, nazwisko: String): List<Kierowca> = query{
        KierowcaTable
            .selectAll().where { (KierowcaTable.imie like "$imie%") and (KierowcaTable.nazwisko like "$nazwisko%") }
            .map {it.toKierowca()}
    }

    override suspend fun createKierowca(kierowca: Kierowca): Int = query{
        KierowcaTable.insertAndGetId {
            it[imie] = kierowca.imie
            it[nazwisko] = kierowca.nazwisko
            it[dataUrodzenia] = kierowca.dataUrodzenia.toJavaLocalDate()
            it[dataWaznosciPrawaJazdy] = kierowca.dataWaznosciPrawaJazdy.toJavaLocalDate()
            it[dataZatrudnienia] = kierowca.dataZatrudnienia.toJavaLocalDate()
            it[pesel] = kierowca.pesel
            it[nrTelefonu] = kierowca.nrTelefonu
        }.value
    }

    override suspend fun deleteKierowca(id: Int): Boolean = query {
        KierowcaTable.deleteWhere { KierowcaTable.id eq id } > 0
    }

//    override suspend fun updateKierwoca() {
//        TODO("Not yet implemented")
//    }
}
