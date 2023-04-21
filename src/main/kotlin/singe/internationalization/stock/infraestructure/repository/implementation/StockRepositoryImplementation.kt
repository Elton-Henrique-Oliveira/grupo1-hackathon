package br.com.lince.singe.internationalization.stock.infraestructure.repository.implementation


import br.com.lince.singe.core.shared.webservice.BasicFilter
import br.com.lince.singe.internationalization.stock.domain.entities.Stock
import br.com.lince.singe.internationalization.stock.domain.repository.StockRepository
import br.com.lince.singe.internationalization.stock.domain.usecases.response.StockFilter
import br.com.lince.singe.internationalization.stock.domain.usecases.response.StockListResponse
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class StockRepositoryImplementation(
) : StockRepository {
    override fun createStock(stockInsert: Stock) {
        TODO("Not yet implemented")
    }

    override fun getCountRequestStockTransfer(filter: List<BasicFilter>?): Int {
        TODO("Not yet implemented")
    }

    override fun create(stock: Stock): Stock? {
        TODO("Not yet implemented")
    }

    override fun getStockByID(stockID: UUID): StockListResponse? {
        TODO("Not yet implemented")
    }

    override fun listAllStock(
        page: Int,
        size: Int,
        sortBy: String,
        orderBy: String,
        groupWarehouse: Boolean,
        filters: List<StockFilter>?
    ): List<StockListResponse> {
        TODO("Not yet implemented")
    }

    override fun getCountAllStock(
        page: Int,
        size: Int,
        sortBy: String,
        orderBy: String,
        groupWarehouse: Boolean,
        filters: List<StockFilter>?
    ): Int {
        TODO("Not yet implemented")
    }

    override fun confirmStockTransfer(stockTransferUUID: UUID, userUUID: UUID) {
        TODO("Not yet implemented")
    }

    override fun stockWriteOff(stockTransferUUID: UUID, destinyWarehouse: UUID) {
        TODO("Not yet implemented")
    }

    override fun countStockDetailed(filter: List<BasicFilter>?, productCode: String): Int {
        TODO("Not yet implemented")
    }

    override fun rollbackStandby(stockTransferUUID: UUID, userUUID: UUID) {
        TODO("Not yet implemented")
    }

    override fun rollbackReceived(stockTransferUUID: UUID, userUUID: UUID) {
        TODO("Not yet implemented")
    }
}

