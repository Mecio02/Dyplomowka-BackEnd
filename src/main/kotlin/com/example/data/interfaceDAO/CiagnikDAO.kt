package com.example.data.interfaceDAO

import com.example.data.model.Ciagnik

interface CiagnikDAO {
    suspend fun getAllCiagniki(): List<Ciagnik>
    suspend fun getCiagnikbyID(id: Int): Ciagnik?
    suspend fun getCiagnikiByMarka(marka: String): List<Ciagnik>
    suspend fun getCiagnikiByDMC(dmc: Int): List<Ciagnik>
    suspend fun getCiagnikiByTablicaRejestracyjna(nrTablicy: String): List<Ciagnik>
    suspend fun deleteCiagnik(id: Int): Boolean
    suspend fun createCiagnik(ciagnik: Ciagnik): Int
}