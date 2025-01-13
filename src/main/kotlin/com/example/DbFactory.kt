package com.example

import com.example.data.model.*
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

fun hikari(url: String, user: String, password: String, pool: Int): HikariDataSource{
    val config = HikariConfig()
        config.driverClassName = "org.postgresql.Driver"
        config.jdbcUrl = url
        config.username = user
        config.password = password
        config.maximumPoolSize = pool
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return HikariDataSource(config)
}

object DbFactory {
    fun init (environment: ApplicationEnvironment){
        val url = environment.config.property("postgres.url").getString()
        val user = environment.config.property("postgres.user").getString()
        val password = environment.config.property("postgres.password").getString()
        val pool = environment.config.property("postgres.pool").getString().toInt()

        Database.connect(hikari(url,user,password,pool))

        transaction {
            //
            addLogger(StdOutSqlLogger) // Zobaczymy czy działa jak nie to wywalić
            //
            SchemaUtils.create(KierowcaTable)
            SchemaUtils.create(TrasaTable)
            SchemaUtils.create(NaczepaTable)
            SchemaUtils.create(ZmianyStatusuTable)
            SchemaUtils.create(CiagnikTable)
        }   
    }
}

suspend fun<T> query(
    block: ()  -> T
): T = withContext(Dispatchers.IO){
    transaction { block() }
}
