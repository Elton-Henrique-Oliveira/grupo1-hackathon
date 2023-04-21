package br.com.lince.singe.internationalization.stock.infraestructure.webservice.implementation


import StockResponse
import br.com.lince.singe.core.shared.error.GenericError
import br.com.lince.singe.core.shared.webservice.BasicFilter
import br.com.lince.singe.internationalization.stock.domain.entities.Stock
import br.com.lince.singe.internationalization.stock.domain.usecases.StockUseCase
import br.com.lince.singe.internationalization.stock.domain.usecases.response.*
import br.com.lince.singe.internationalization.stock.infraestructure.webservice.StockService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/stock")
class StockServiceImplementation(
    val usecase: StockUseCase,
) : StockService {
    @PostMapping("/{stockTransferUUID}")
    override fun confirmStockTransfer(
        @PathVariable stockTransferUUID: UUID,
    ) {

    }

    @PostMapping("/transfer/{originWarehouse}/{destinyWarehouse}")
    override fun transfer(
        @PathVariable originWarehouse: UUID?,
        @PathVariable destinyWarehouse: UUID?,
    ): TransferResponse? {
        return null
    }

    @GetMapping("/transfer")
    override fun listStockTranfer(
        @RequestParam("page", required = true, defaultValue = "1") page: Int,
        @RequestParam("size", required = true, defaultValue = "30") size: Int,
        @RequestParam("orderBy", required = false, defaultValue = "") orderBy: String,
        @RequestParam("sortBy", required = false, defaultValue = "desc") sortBy: String,
        @RequestParam("filter", required = false, defaultValue = "") filter: List<BasicFilter>?,
    ): StockTransferResponse {
        return usecase.listStockTransfer(
            page,
            size,
            orderBy,
            sortBy,
            filter
        )
    }


    @PostMapping
    override fun create(@RequestBody stock: Stock): StockResponse {
        return usecase.save(stock)
    }

    @GetMapping("/{stockID}")
    override fun getStockByID(@PathVariable("stockID") stockID: UUID): StockListResponse? {
        return usecase.getById(stockID)
    }

    @GetMapping("")
    override fun listStock(
        @RequestParam("page", required = false, defaultValue = "1") page: Int,
        @RequestParam("size", required = false, defaultValue = "30") size: Int,
        @RequestParam("orderBy", required = false, defaultValue = "") orderBy: String,
        @RequestParam("sortBy", required = false, defaultValue = "asc") sortBy: String,
        @RequestParam("groupWarehouse", required = false, defaultValue = "false") groupWarehouse: Boolean,
        @RequestParam("filter", required = false) filter: List<BasicFilter>?,
    ): StockListAllResponse? {
        return usecase.listAllStock(
            page = page,
            size = size,
            orderBy = orderBy,
            sortBy = sortBy,
            groupWarehouse = groupWarehouse,
            filter?.map { StockFilter(it.name, it.value) }
        )
    }

    @GetMapping("/{uuid}/detailed")
    override fun getStockTransferDetailed(@PathVariable("uuid") requestUUID: UUID): TransferResponse {
        return usecase.getStockTransferDetailed(requestUUID)
    }

    @GetMapping("/list/{productCode}/detailed")
    override fun listStockDetailed(
        @RequestParam("page", required = false, defaultValue = "1") page: Int,
        @RequestParam("size", required = false, defaultValue = "30") size: Int,
        @RequestParam("orderBy", required = false, defaultValue = "") orderBy: String,
        @RequestParam("sortBy", required = false, defaultValue = "asc") sortBy: String,
        @RequestParam("filter", required = false) filter: List<BasicFilter>?,
        @PathVariable("productCode") productCode: String,
    ): StockDetailedResponse {
        return usecase.listProductStockDetailed(
            page,
            size,
            orderBy,
            sortBy,
            filter,
            productCode
        )
    }

    @PostMapping("/cancel/{stockTransferUUID}")
    override fun cancelStockTransfer(
        @PathVariable("stockTransferUUID") stockTransferUUID: UUID,
    ): GenericError? {
        return null
    }

    @GetMapping("/transfer/history/{stockTransferUUID}")
    override fun listStockTransferHistory(
        @PathVariable("stockTransferUUID") stockTransferUUID: UUID,
    ): StockTransferHistoryResponse {
        return usecase.listStockTransferHistory(stockTransferUUID)
    }
}