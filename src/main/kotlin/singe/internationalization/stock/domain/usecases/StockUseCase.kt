package br.com.lince.singe.internationalization.stock.domain.usecases

import StockResponse
import br.com.lince.singe.core.shared.error.GenericError
import br.com.lince.singe.core.shared.webservice.BasicFilter
import br.com.lince.singe.internationalization.stock.domain.entities.Stock
import br.com.lince.singe.internationalization.stock.domain.usecases.response.*
import java.util.*

interface StockUseCase {

    fun listStockTransfer(
        page: Int,
        size: Int,
        orderBy: String,
        sortBy: String,
        filter: List<BasicFilter>?,
    ): StockTransferResponse

    fun save(stock: Stock): StockResponse

    fun getById(stockUUID: UUID): StockListResponse?

    fun listAllStock(
        page: Int,
        size: Int,
        orderBy: String,
        sortBy: String,
        groupWarehouse: Boolean,
        filters: List<StockFilter>?,
    ): StockListAllResponse?

    fun confirmStockTransfer(stockTransferUUID: UUID, userUUID: UUID)

    fun getStockTransferDetailed(uuid: UUID): TransferResponse

    fun listProductStockDetailed(
        page: Int,
        size: Int,
        orderBy: String,
        sortBy: String,
        filter: List<BasicFilter>?,
        productCode: String,
    ): StockDetailedResponse

    fun listStockTransferHistory(stockTransferUUID: UUID): StockTransferHistoryResponse
}