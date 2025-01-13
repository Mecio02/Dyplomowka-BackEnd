package com.example.data.interfaceDAO

import com.example.data.model.Kierowca

interface KierowcaDAO {
    suspend fun getAllKierowcy(): List<Kierowca>
    suspend fun getKierowca(id: Int): Kierowca?
    suspend fun getKierowcaByName(imie: String, nazwisko: String): List<Kierowca>
    suspend fun createKierowca(kierowca: Kierowca): Int
    suspend fun deleteKierowca(id: Int): Boolean
//    suspend fun updateKierwoca()
}



