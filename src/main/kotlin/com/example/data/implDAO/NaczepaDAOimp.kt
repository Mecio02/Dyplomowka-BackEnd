package com.example.data.implDAO

import com.example.data.interfaceDAO.NaczepaDAO
import com.example.data.model.Naczepa
import com.example.data.model.NaczepaTable
import com.example.query
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toKotlinLocalDate
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll

class NaczepaDAOimp : NaczepaDAO {

    private fun ResultRow.toNaczepy() = Naczepa(
        id = this[NaczepaTable.id].value,
        nrTablicyRejestracyjnej = this[NaczepaTable.nrTablicyRejestracyjnej],
        masaWlasna = this[NaczepaTable.masaWlasna],
        dopuszczalnaMasaCalkowita = this[NaczepaTable.dopuszczalnaMasaCalkowita],
        rodzajOpon = this[NaczepaTable.rodzajOpon],
        typ = this[NaczepaTable.typ],
        iloscOsi = this[NaczepaTable.iloscOsi],
        dataWaznosciPrzegladu = this[NaczepaTable.dataWaznosciPrzegladu].toKotlinLocalDate(),
        vin = this[NaczepaTable.vin],
        dataZakupu = this[NaczepaTable.dataZakupu].toKotlinLocalDate(),
        dataPierwszejRejestracji = this[NaczepaTable.dataPierwszejRejestracji].toKotlinLocalDate()
    )

    override suspend fun getAllNaczepy(): List<Naczepa> = query {
        NaczepaTable.selectAll().map { it.toNaczepy() }
    }

    override suspend fun getNaczepyByID(id: Int): Naczepa? = query{
        NaczepaTable
            .selectAll().where(NaczepaTable.id eq id)
            .map { it.toNaczepy() }
            .singleOrNull()
    }

    override suspend fun getNaczepyByNrTablicyRejestracynej(nrTablicy: String): List<Naczepa> = query{
        NaczepaTable
            .selectAll().where { NaczepaTable.nrTablicyRejestracyjnej like "$nrTablicy%"}
            .map { it.toNaczepy() }
    }

    override suspend fun deleteNaczepa(id: Int): Boolean = query {
        NaczepaTable.deleteWhere { NaczepaTable.id eq id } > 0
    }

    override suspend fun createNaczepa(naczepa: Naczepa): Int = query{
        NaczepaTable.insertAndGetId {
            it[nrTablicyRejestracyjnej] = naczepa.nrTablicyRejestracyjnej
            it[masaWlasna] = naczepa.masaWlasna
            it[dopuszczalnaMasaCalkowita] = naczepa.dopuszczalnaMasaCalkowita
            it[rodzajOpon] = naczepa.rodzajOpon
            it[typ] = naczepa.typ
            it[iloscOsi] = naczepa.iloscOsi
            it[dataWaznosciPrzegladu] = naczepa.dataWaznosciPrzegladu.toJavaLocalDate()
            it[vin] = naczepa.vin
            it[dataZakupu] = naczepa.dataZakupu.toJavaLocalDate()
            it[dataPierwszejRejestracji] = naczepa.dataPierwszejRejestracji.toJavaLocalDate()
        }.value
    }
}