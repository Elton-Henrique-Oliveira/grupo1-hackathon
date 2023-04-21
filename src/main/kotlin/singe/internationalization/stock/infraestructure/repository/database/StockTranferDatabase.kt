package br.com.lince.singe.internationalization.stock.infraestructure.repository.database

import br.com.lince.singe.core.shared.utils.Utils
import br.com.lince.singe.core.shared.webservice.BasicFilter
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime

object StockTransferDatabase : Table("stock_transfer") {
    var uuid = uuid("uuid").uniqueIndex()
    var code = integer("code").autoIncrement().uniqueIndex()
    var receivedAt = datetime("received_at").nullable()
    var statusCode = integer("status_code").default(0)
    val modifiedAt = datetime("modified_at").defaultExpression(CurrentDateTime)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)

    init {
        PrimaryKey(uuid)
    }
}

object StockTransferBoxesDatabase : Table("stock_transfer_boxes") {
    var transferUUID = reference("transfer_uuid", StockTransferDatabase.uuid)
    var statusCode = integer("status_code").default(0)
    val modifiedAt = datetime("modified_at").defaultExpression(CurrentDateTime)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
}

fun Query.withStockTransferFilter(filter: List<BasicFilter>?): Query {
    if (filter == null) {
        return this
    }
    val filters = filter.map {
        when (it.name) {
            "uuid" -> Op.build { StockTransferDatabase.uuid eq Utils.uuidOrEmpty(it.value) }
            "code" -> Op.build { StockTransferDatabase.code eq it.value.toInt() }
            "statusCode" -> Op.build { StockTransferDatabase.statusCode eq it.value.toInt() }
            "createdAt" -> Op.build {
                StockTransferDatabase.createdAt greaterEq Utils.dateConverter(it.value) and (StockTransferDatabase.createdAt lessEq Utils.dateConverterMax(
                    it.value
                ))
            }

            "modifiedAtDate" -> Op.build {
                StockTransferDatabase.modifiedAt greaterEq Utils.dateConverter(it.value) and (StockTransferDatabase.modifiedAt lessEq Utils.dateConverterMax(
                    it.value
                ))
            }

            else -> throw Exception("invalid column name: ${it.name}")
        }
    }

    filters.forEach { this.andWhere { it } }

    return this
}
