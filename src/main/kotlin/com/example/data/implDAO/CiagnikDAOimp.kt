package com.example.data.implDAO

import com.example.data.interfaceDAO.CiagnikDAO
import com.example.data.model.Ciagnik
import com.example.data.model.CiagnikTable
import com.example.query
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toKotlinLocalDate
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll

class CiagnikDAOimp : CiagnikDAO {

    //Wykorzystujemy wcześniej przygotowany Interface aby zaimplementowac odpowiednie funkcje DAO

    // Funkcja mapująca Obiektu typu ciagnik do ResultRow, co pozwala na uzycie
    // funkcji przy wywołaniu obiektu zamiast ponownego przypisywania dancyh
    private fun ResultRow.toCiagnik() = Ciagnik(
        id = this[CiagnikTable.id].value,
        nrTablicyRejestracyjnej = this[CiagnikTable.nrTablicyRejestracyjnej],
        marka = this[CiagnikTable.marka],
        model = this[CiagnikTable.model],
        iloscOsi = this[CiagnikTable.iloscOsi],
        dopuszczalnaMasaCalkowita = this[CiagnikTable.dopuszczalnaMasaCalkowita],
        masaPojazdu = this[CiagnikTable.masaPojazdu],
        dataWaznosciPrzegladu = this[CiagnikTable.dataWaznosciPrzegladu].toKotlinLocalDate(),
        rodzajOpon = this[CiagnikTable.rodzajOpon],
        typSkrzyni = this[CiagnikTable.typSkrzyni],
        vin = this[CiagnikTable.vin],
        zasiegMin = this[CiagnikTable.zasiegMin],
        zasiegMax = this[CiagnikTable.zasiegMax],
        dataZakupuPojazdu = this[CiagnikTable.dataZakupuPojazdu].toKotlinLocalDate(),
        typPojazdu = this[CiagnikTable.typPojazdu],
        stanPojazdu = this[CiagnikTable.stanPojazdu],
        opis = this[CiagnikTable.opis],
        normaEmisji = this[CiagnikTable.normaEmisji],
        kolor = this[CiagnikTable.kolor],
        liczbaMiejsc = this[CiagnikTable.liczbaMiejsc],
        wlasciciel = this[CiagnikTable.wlasciciel],
        dataGwarancji = this[CiagnikTable.dataGwarancji].toKotlinLocalDate(),
        rodzajSilnika = this[CiagnikTable.rodzajSilnika],
        rodzajPaliwa = this[CiagnikTable.rodzajPaliwa],
        rocznyLimitKm = this[CiagnikTable.rocznyLimitKm],
        dataPierwszejRejestracji = this[CiagnikTable.dataPierwszejRejestracji].toKotlinLocalDate(),
        nrKartyPaliwowej = this[CiagnikTable.nrKartyPaliwowej]
    )


    override suspend fun getAllCiagniki(): List<Ciagnik> = query {
        CiagnikTable.selectAll().map { it.toCiagnik() }
    }

    override suspend fun getCiagnikbyID(id: Int): Ciagnik? = query {
        CiagnikTable
            .selectAll().where(CiagnikTable.id eq id)
            .map { it.toCiagnik() }
            .singleOrNull()
    }

    override suspend fun getCiagnikiByDMC(dmc: Int): List<Ciagnik> = query {
        CiagnikTable
            .selectAll().where { CiagnikTable.dopuszczalnaMasaCalkowita greaterEq dmc }
            .map { it.toCiagnik() }
    }

    override suspend fun getCiagnikiByMarka(marka: String): List<Ciagnik> = query {
        CiagnikTable
            .selectAll().where { CiagnikTable.marka like "$marka%"}
            .map { it.toCiagnik() }
    }

    override suspend fun getCiagnikiByTablicaRejestracyjna(nrTablicy: String): List<Ciagnik> = query{
        CiagnikTable
            .selectAll().where { CiagnikTable.nrTablicyRejestracyjnej like "$nrTablicy%"}
            .map { it.toCiagnik() }
    }

    override suspend fun deleteCiagnik(id: Int): Boolean = query {
        CiagnikTable.deleteWhere { CiagnikTable.id eq id } > 0
    }

    override suspend fun createCiagnik(ciagnik: Ciagnik): Int = query{
        CiagnikTable.insertAndGetId {
            it[nrTablicyRejestracyjnej] = ciagnik.nrTablicyRejestracyjnej
            it[marka] = ciagnik.marka
            it[model] = ciagnik.model
            it[iloscOsi] = ciagnik.iloscOsi
            it[dopuszczalnaMasaCalkowita] = ciagnik.dopuszczalnaMasaCalkowita
            it[masaPojazdu] = ciagnik.masaPojazdu
            it[dataWaznosciPrzegladu] = ciagnik.dataWaznosciPrzegladu.toJavaLocalDate()
            it[rodzajOpon] = ciagnik.rodzajOpon
            it[typSkrzyni] = ciagnik.typSkrzyni
            it[vin] = ciagnik.vin
            it[zasiegMin] = ciagnik.zasiegMin
            it[zasiegMax] = ciagnik.zasiegMax
            it[dataZakupuPojazdu] = ciagnik.dataZakupuPojazdu.toJavaLocalDate()
            it[typPojazdu] = ciagnik.typPojazdu
            it[stanPojazdu] = ciagnik.stanPojazdu
            it[opis] = ciagnik.opis
            it[normaEmisji] = ciagnik.normaEmisji
            it[kolor] = ciagnik.kolor
            it[liczbaMiejsc] = ciagnik.liczbaMiejsc
            it[wlasciciel] = ciagnik.wlasciciel
            it[dataGwarancji] = ciagnik.dataGwarancji.toJavaLocalDate()
            it[rodzajSilnika] = ciagnik.rodzajSilnika
            it[rodzajPaliwa] = ciagnik.rodzajPaliwa
            it[rocznyLimitKm] = ciagnik.rocznyLimitKm
            it[dataPierwszejRejestracji] = ciagnik.dataPierwszejRejestracji.toJavaLocalDate()
            it[nrKartyPaliwowej] = ciagnik.nrKartyPaliwowej
        }.value
    }
}