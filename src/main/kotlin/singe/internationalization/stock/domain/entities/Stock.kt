package br.com.lince.singe.internationalization.stock.domain.entities

import java.time.LocalDateTime
import java.util.*

class Stock(
    var uuid: UUID?,
    var variantUUID: UUID? = null,
    var warehouseUUID: UUID? = null,
    var variantQuantity: Int? = 0,
    var boxUUID: UUID? = null,
    var statusCode: Int? = 0,
    var modifiedAt: LocalDateTime? = null,
    var createdAt: LocalDateTime? = null,
)
