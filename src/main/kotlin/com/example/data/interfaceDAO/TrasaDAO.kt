package com.example.data.interfaceDAO

import com.example.data.model.Trasa

interface TrasaDAO {
    suspend fun getAllTrasy(): List<Trasa>
    suspend fun getTrasyByID(id: Int): Trasa?
    suspend fun getTrasyByCiagnik(id: Int): List<Trasa>
    suspend fun getTrasyByKierowca(id: Int): List<Trasa>
    suspend fun getTrasaByNaczepa(id: Int): List<Trasa>
    suspend fun getTrasaByOdbiorca(nazwa: String): List<Trasa>
    suspend fun getTrasaByNadawca(nazwa: String): List<Trasa>
    suspend fun deleteTrasa(id: Int): Boolean
    suspend fun createTrasa(trasa: Trasa): Int
}