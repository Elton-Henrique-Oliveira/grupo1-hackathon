package br.com.lince.singe.internationalization.stock.domain.usecases.response

import java.time.LocalDateTime
import java.util.*

data class StockListResponse(
    val stockUUID: UUID? = null,
    val totalQuantity: Int? = null,
    val requestedQuantity: Int? = null,
    val availableQuantity: Int? = null,
    var statusCode: Int? = 0,
    var modifiedAt: LocalDateTime? = null,
    var createdAt: LocalDateTime? = null,
) {
    override fun toString(): String {
        return "StockListResponse=($stockUUID, $totalQuantity, $statusCode, $modifiedAt, $createdAt)"
    }
}