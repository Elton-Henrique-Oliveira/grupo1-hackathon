package br.com.lince.singe.internationalization.stock.domain.repository


import br.com.lince.singe.core.shared.webservice.BasicFilter
import br.com.lince.singe.internationalization.stock.domain.entities.Stock
import br.com.lince.singe.internationalization.stock.domain.usecases.response.StockFilter
import br.com.lince.singe.internationalization.stock.domain.usecases.response.StockListResponse
import java.util.*

interface StockRepository {
    fun createStock(stockInsert: Stock)

    fun getCountRequestStockTransfer(filter: List<BasicFilter>?): Int

    fun create(stock: Stock): Stock?

    fun getStockByID(stockID: UUID): StockListResponse?

    fun listAllStock(
        page: Int,
        size: Int,
        sortBy: String,
        orderBy: String,
        groupWarehouse: Boolean,
        filters: List<StockFilter>?,
    ): List<StockListResponse>

    fun getCountAllStock(
        page: Int,
        size: Int,
        sortBy: String,
        orderBy: String,
        groupWarehouse: Boolean,
        filters: List<StockFilter>?,
    ): Int

    fun confirmStockTransfer(stockTransferUUID: UUID, userUUID: UUID)

    fun stockWriteOff(stockTransferUUID: UUID, destinyWarehouse: UUID)


    fun countStockDetailed(filter: List<BasicFilter>?, productCode: String): Int

    fun rollbackStandby(stockTransferUUID: UUID, userUUID: UUID)

    fun rollbackReceived(stockTransferUUID: UUID, userUUID: UUID)

}