package com.example.data.interfaceDAO

import com.example.data.model.ZmianyStatusu
import kotlinx.datetime.LocalDateTime
import java.math.BigDecimal

interface ZmianaStatusuDAO {
    suspend fun getAllZmianyStatusu(): List<ZmianyStatusu>
    suspend fun getZmianaStatusuByID(id: Int): ZmianyStatusu?
    suspend fun getZmianaStatusuByTrasa(id: Int): ZmianyStatusu?
    suspend fun getZmianaStatusuByStatus(status: Int): List<ZmianyStatusu>
    suspend fun getZmianaStatusuByData(data: LocalDateTime): List<ZmianyStatusu>
    suspend fun getZmianaStatusuByKoszt(koszt: BigDecimal, widelki: BigDecimal): List<ZmianyStatusu>
    suspend fun deleteStatus(id: Int): Boolean
    suspend fun createStatus(statusy: ZmianyStatusu): Int
}