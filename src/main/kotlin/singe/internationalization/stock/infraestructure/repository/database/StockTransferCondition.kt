package br.com.lince.singe.internationalization.stock.infraestructure.repository.database

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime

object StockTransferConditionDatabase : Table("stock_transfer_condition") {
    val uuid = uuid("transfer_uuid").uniqueIndex()
    val code = integer("code")
    val description = varchar("description", 200)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
}

enum class EnumStockTransferCondition(val value: Int) {
    //Used when the user create the request
    ConditionCreatedRequest(0),

    //Used when the user confirm that received the request
    ConditionReceivedRequest(1),

    //Used when the user makes a wrong stock transfer request
    ConditionDeletedRequest(2),

    //Used when the user already confirms receipt and wants to cancel
    ConditionCanceledRequest(3),
}