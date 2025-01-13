package com.example.data.interfaceDAO

import com.example.data.model.Naczepa

interface NaczepaDAO {
    suspend fun getAllNaczepy(): List<Naczepa>
    suspend fun getNaczepyByID(id: Int): Naczepa?
    suspend fun getNaczepyByNrTablicyRejestracynej(nrTablicy: String): List<Naczepa>
    suspend fun deleteNaczepa(id: Int): Boolean
    suspend fun createNaczepa(naczepa: Naczepa): Int
}