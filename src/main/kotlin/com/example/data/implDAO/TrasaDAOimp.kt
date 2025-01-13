package com.example.data.implDAO

import com.example.data.interfaceDAO.TrasaDAO
import com.example.data.model.*
import com.example.query
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toKotlinLocalDate
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.like
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll

class TrasaDAOimp: TrasaDAO {

    //funkcja pomocnicza, pozawala na mapowanie danych, zmniejsza ilosc potrzeby powtarzania sie
    private fun ResultRow.toTrasy() = Trasa(
        id = this[TrasaTable.id].value,
        idCiagnika = this[TrasaTable.idCiagnika],
        idKierowcy = this[TrasaTable.idKierowcy],
        idNaczepy = this[TrasaTable.idNaczepy],
        lokalizacjaKontrahentOdbiorcy = this[TrasaTable.lokalizacjaKontrahentOdbiorcy],
        kontrahentOdbiorca = this[TrasaTable.kontrahentOdbiorca],
        miejsceOdbioruPojazdu = this[TrasaTable.miejsceOdbioruPojazdu],
        dataOdbioruPojazdu = this[TrasaTable.dataOdbioruPojazdu].toKotlinLocalDate(),
        odczytLicznikaKmWyjazd = this[TrasaTable.odczytLicznikaKmWyjazd],
        odczytLicznikaKmDojazd = this[TrasaTable.odczytLicznikaKmDojazd],
        opis = this[TrasaTable.opis],
        kontrahentNadawca = this[TrasaTable.kontrahentNadawca],
        lokalizacjaKontrahentaNadawcy = this[TrasaTable.lokalizacjaKontrahentaNadawcy],
    )

    override suspend fun getAllTrasy(): List<Trasa> = query{
        TrasaTable
            .selectAll()
            .map { it.toTrasy() }
    }

    override suspend fun getTrasaByNaczepa(id: Int): List<Trasa> = query{
        TrasaTable
            .selectAll().where(TrasaTable.idNaczepy eq id)
            .map { it.toTrasy() }
    }

    override suspend fun getTrasyByCiagnik(id: Int): List<Trasa> = query{
        TrasaTable
            .selectAll().where(TrasaTable.idCiagnika eq id)
            .map { it.toTrasy() }
    }

    override suspend fun getTrasaByNadawca(nazwa: String): List<Trasa> = query {
        TrasaTable
            .selectAll().where(TrasaTable.kontrahentNadawca like "$nazwa%")
            .map { it.toTrasy() }
    }

    override suspend fun getTrasaByOdbiorca(nazwa: String): List<Trasa> = query {
        TrasaTable
            .selectAll().where(TrasaTable.kontrahentOdbiorca like "$nazwa%")
            .map { it.toTrasy() }
    }

    override suspend fun getTrasyByID(id: Int): Trasa? = query {
        TrasaTable
            .selectAll().where(TrasaTable.id eq id)
            .map { it.toTrasy() }
            .singleOrNull()
    }

    override suspend fun getTrasyByKierowca(id: Int): List<Trasa> = query {
        TrasaTable
            .selectAll().where(TrasaTable.idKierowcy eq id)
            .map { it.toTrasy() }
    }

    override suspend fun deleteTrasa(id: Int): Boolean = query{
        TrasaTable.deleteWhere { TrasaTable.id eq id } > 0
    }

    override suspend fun createTrasa(trasa: Trasa): Int = query{
        TrasaTable.insertAndGetId {
            it[idCiagnika] = trasa.idCiagnika
            it[idNaczepy] = trasa.idNaczepy
            it[idKierowcy] = trasa.idKierowcy
            it[lokalizacjaKontrahentOdbiorcy] = trasa.lokalizacjaKontrahentOdbiorcy
            it[kontrahentOdbiorca] = trasa.kontrahentOdbiorca
            it[miejsceOdbioruPojazdu] = trasa.miejsceOdbioruPojazdu
            it[dataOdbioruPojazdu] = trasa.dataOdbioruPojazdu.toJavaLocalDate()
            it[odczytLicznikaKmWyjazd] = trasa.odczytLicznikaKmWyjazd
            it[odczytLicznikaKmDojazd] = trasa.odczytLicznikaKmDojazd
            it[opis] = opis
            it[kontrahentNadawca] = trasa.kontrahentNadawca
            it[lokalizacjaKontrahentaNadawcy] = trasa.lokalizacjaKontrahentOdbiorcy
        }.value
    }
}