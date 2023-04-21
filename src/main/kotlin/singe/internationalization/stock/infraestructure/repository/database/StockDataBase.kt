package br.com.lince.singe.internationalization.stock.infraestructure.repository.database

import br.com.lince.singe.core.shared.utils.Utils
import br.com.lince.singe.core.shared.webservice.BasicFilter
import br.com.lince.singe.internationalization.stock.domain.usecases.response.StockFilter
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime
import java.util.*

object StockDatabase : Table("stock") {
    var uuid = uuid("uuid").uniqueIndex()
    var statusCode = integer("status_code").default(0)
    val modifiedAt = datetime("modified_at").defaultExpression(CurrentDateTime)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)

    init {
        PrimaryKey(uuid)
    }
}

fun Query.withStockFilters(filter: List<StockFilter>?): Query {
    if (filter == null) {
        return this
    }
    val filters = filter.map {
        when (it.name) {
            "uuid" -> Op.build { StockDatabase.uuid eq Utils.uuidOrEmpty(it.value) }
            "statusCode" -> Op.build { StockDatabase.statusCode eq it.value.toInt() }
            else -> throw Exception("invalid column name: ${it.name}")
        }
    }
    filters.forEach { this.andWhere { it } }
    return this
}

fun Query.withStockDetailedFilters(filter: List<BasicFilter>?): Query {
    val filters = filter?.map {
        println("foudace" + it.value)
        when (it.name) {
            else -> throw Exception("invalid column name: ${it.name}")
        }
    }
    filters?.forEach { this.andWhere { it } }
    return this
}