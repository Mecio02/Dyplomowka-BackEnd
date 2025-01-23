package com.example.data.implDAO

import com.example.data.interfaceDAO.ZmianaStatusuDAO
import com.example.data.model.ZmianyStatusu
import com.example.data.model.ZmianyStatusuTable
import com.example.query
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toKotlinLocalDateTime
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.math.BigDecimal

class ZmianaStatusuDAOimp: ZmianaStatusuDAO {
    private fun ResultRow.toZmaianyStatusu() = ZmianyStatusu(
        id = this[ZmianyStatusuTable.id].value,
        idTrasy = this[ZmianyStatusuTable.idTrasy],
        status = this[ZmianyStatusuTable.status],
        dataZmianaStatusu = this[ZmianyStatusuTable.dataZmianaStatusu].toKotlinLocalDateTime(),
        lokalizacja = this[ZmianyStatusuTable.lokalizacja],
        dokladnyOpis = this[ZmianyStatusuTable.dokladnyOpis],
        koszt = this[ZmianyStatusuTable.koszt]
    )

    override suspend fun getAllZmianyStatusu(): List<ZmianyStatusu> = query {
        ZmianyStatusuTable
            .selectAll()
            .map { it.toZmaianyStatusu() }
    }

//    override suspend fun getZmianaStatusuByData(data: LocalDateTime): List<ZmianyStatusu> = query {
//        ZmianyStatusuTable
//            .selectAll().where(ZmianyStatusuTable.dataZmianaStatusu eq data)
//            .map { it.toZmaianyStatusu() }
//    }
    override suspend fun getZmianaStatusuByData(data: LocalDateTime): List<ZmianyStatusu> = query {
        TODO("Not yet implemented")
    }

    override suspend fun getZmianaStatusuByID(id: Int): ZmianyStatusu? = query {
        ZmianyStatusuTable
            .selectAll().where(ZmianyStatusuTable.id eq id)
            .map { it.toZmaianyStatusu() }
            .singleOrNull()
    }

    override suspend fun getZmianaStatusuByStatus(status: Int): List<ZmianyStatusu> = query {
        ZmianyStatusuTable
            .selectAll().where(ZmianyStatusuTable.status eq status)
            .map { it.toZmaianyStatusu() }
    }

    override suspend fun getZmianaStatusuByTrasa(id: Int): List<ZmianyStatusu> = query {
        ZmianyStatusuTable
            .selectAll().where(ZmianyStatusuTable.idTrasy eq id)
            .map { it.toZmaianyStatusu() }
    }

    override suspend fun getZmianaStatusuByKoszt(koszt: BigDecimal, widelki: BigDecimal): List<ZmianyStatusu> = query {
        ZmianyStatusuTable
            .selectAll().where { (ZmianyStatusuTable.koszt greaterEq (koszt + widelki)) and (ZmianyStatusuTable.koszt greaterEq (koszt - widelki))}
            .map { it.toZmaianyStatusu() }
    }

    override suspend fun deleteStatus(id: Int): Boolean = query{
        ZmianyStatusuTable.deleteWhere { ZmianyStatusuTable.id eq id } > 0
    }

    override suspend fun createStatus(statusy: ZmianyStatusu): Int = query {
        ZmianyStatusuTable.insertAndGetId {
            it[idTrasy] = statusy.idTrasy
            it[status] = statusy.status
            it[dataZmianaStatusu] = statusy.dataZmianaStatusu.toJavaLocalDateTime()
            it[lokalizacja] = statusy.lokalizacja
            it[dokladnyOpis] = statusy.dokladnyOpis
            it[koszt] = statusy.koszt
        }.value
    }
}