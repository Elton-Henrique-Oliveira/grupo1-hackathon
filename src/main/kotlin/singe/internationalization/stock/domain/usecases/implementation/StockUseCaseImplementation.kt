package br.com.lince.singe.internationalization.stock.domain.usecases.implementation

import BOX_ALREADY_REQUESTED
import CANT_TRANSFER_TO_ITSELF
import ERROR_SENDING_EMAIL
import INVALID_BOX_WAREHOUSE
import STOCK_LIST_ERROR
import STOCK_TRANSFER_BOX_NOT_FOUND
import STOCK_TRANSFER_DESTINY_WAREHOUSE_NOT_FOUND
import STOCK_TRANSFER_ORIGIN_WAREHOUSE_NOT_FOUND
import STOCK_TRANSFER_REQUEST_NOT_FOUND
import STOCK_TRANSFER_STORAGE_ERROR
import StockResponse
import WAREHOUSE_IS_NOT_SEPARATION
import WAREHOUSE_IS_SEPARATION
import br.com.lince.singe.core.shared.error.GenericError
import br.com.lince.singe.core.shared.utils.Utils
import br.com.lince.singe.core.shared.webservice.BasicFilter
import br.com.lince.singe.internationalization.stock.domain.entities.Stock
import br.com.lince.singe.internationalization.stock.domain.repository.StockRepository
import br.com.lince.singe.internationalization.stock.domain.usecases.StockUseCase
import br.com.lince.singe.internationalization.stock.domain.usecases.response.*
import com.github.f4b6a3.uuid.UuidCreator
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.thymeleaf.context.Context
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.ceil

@Service
class StockUseCaseImplementation(
    val repository: StockRepository
) : StockUseCase {
    companion object {
        private val logger = LoggerFactory.getLogger(StockUseCaseImplementation::class.java)
    }

    override fun listStockTransfer(
        page: Int,
        size: Int,
        orderBy: String,
        sortBy: String,
        filter: List<BasicFilter>?
    ): StockTransferResponse {
        TODO("Not yet implemented")
    }

    override fun save(stock: Stock): StockResponse {
        return try {
            StockResponse(stock = repository.create(stock))
        } catch (e: Exception) {
            StockResponse(error = STOCK_TRANSFER_BOX_NOT_FOUND)
        }
    }

    override fun getById(stockUUID: UUID): StockListResponse? {
        return repository.getStockByID(stockID = stockUUID)
    }

    override fun listAllStock(
        page: Int,
        size: Int,
        orderBy: String,
        sortBy: String,
        groupWarehouse: Boolean,
        filters: List<StockFilter>?,
    ): StockListAllResponse {
        return try {
            val numberOfRegister = repository.getCountAllStock(
                page,
                size,
                orderBy,
                sortBy,
                groupWarehouse,
                filters
            )

            var numberPages = 0
            if (numberOfRegister != 0) {
                numberPages = numberOfRegister / size

                if (numberOfRegister % size != 0) {
                    numberPages += 1
                }
            }

            StockListAllResponse(
                stock = repository.listAllStock(page, size, sortBy, orderBy, groupWarehouse, filters),
                page = page,
                size = size,
                numberPages = numberPages,
                error = null
            )
        } catch (e: Exception) {
            println(e.toString())
            StockListAllResponse(error = STOCK_LIST_ERROR)
        }
    }

    override fun confirmStockTransfer(stockTransferUUID: UUID, userUUID: UUID) {
        TODO("Not yet implemented")
    }

    override fun getStockTransferDetailed(uuid: UUID): TransferResponse {
        TODO("Not yet implemented")
    }

    override fun listProductStockDetailed(
        page: Int,
        size: Int,
        orderBy: String,
        sortBy: String,
        filter: List<BasicFilter>?,
        productCode: String
    ): StockDetailedResponse {
        TODO("Not yet implemented")
    }

    override fun listStockTransferHistory(stockTransferUUID: UUID): StockTransferHistoryResponse {
        TODO("Not yet implemented")
    }
}