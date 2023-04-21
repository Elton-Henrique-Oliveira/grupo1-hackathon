package br.com.lince.singe.internationalization.stock.infraestructure.repository.database

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object StockTransferHistoryDatabase : Table("stock_transfer_history") {
    var stockTransferUUID = uuid("transfer_uuid").references(StockTransferDatabase.uuid)
    var modificationUUID = uuid("modification").references(StockTransferConditionDatabase.uuid)
    var modifiedAt = datetime("modified_at").defaultExpression(CurrentDateTime)
}