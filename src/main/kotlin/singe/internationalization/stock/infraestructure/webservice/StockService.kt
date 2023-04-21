package br.com.lince.singe.internationalization.stock.infraestructure.webservice

import StockResponse
import br.com.lince.singe.core.shared.error.GenericError
import br.com.lince.singe.core.shared.webservice.BasicFilter
import br.com.lince.singe.internationalization.stock.domain.entities.Stock
import br.com.lince.singe.internationalization.stock.domain.usecases.response.*
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

/**
 * - *English*: This interface defines all functions that stock use cases will use
 * - *Português*: Esta inferface define todas as funções que serão utilizadas pelos casos de uso do inventario
 */
interface StockService {
    /**
     * - *English*: This function is used to create a request stock transfer
     * - *Português*: Esta função é usada para criar uma solicitação de transferência de estoque
     * @param originWarehouse: UUID?
     * - *English*: This parameter is responsible to store the origin warehouse
     * - *Português*: Este parâmetro é responsável por manter o armazém de origem
     * @param destinyWarehouse: UUID?
     * - *English*: This parameter is responsible to store the destiny warehouse
     * - *Português*: Este parâmetro é responsável por manter o armazém de destino
     * @param boxes: List<Box>?
     * - *English*: This parameter contain the boxes that will be transfer
     * - *Português*: Este parâmetro contém as caixas que serão transferidas
     */
    fun transfer(
        @PathVariable originWarehouse: UUID?,
        @PathVariable destinyWarehouse: UUID?,
    ): TransferResponse?

    fun listStockTranfer(
        @RequestParam("page", required = true, defaultValue = "1") page: Int,
        @RequestParam("size", required = true, defaultValue = "30") size: Int,
        @RequestParam("orderBy", required = false, defaultValue = "") orderBy: String,
        @RequestParam("sortBy", required = false, defaultValue = "desc") sortBy: String,
        @RequestParam("filter", required = false, defaultValue = "") filter: List<BasicFilter>?,
    ): StockTransferResponse

    /**
     * - *English*: This function is used for verification of all use cases in creating the Stock record
     * - *Português*: Esta função é usada para verificação de todos os casos de uso na criação do registro de inventario
     * @param stock Stock
     * - *English*: This parameter will receive all the necessary data for creating the stock record
     * - *Português*: Este parâmetro receberá todos os dados necessários para a criação do registro de inventario
     */
    fun create(@RequestBody stock: Stock): StockResponse

    fun getStockByID(stockID: UUID): StockListResponse?

    fun listStock(
        @RequestParam("page", required = false, defaultValue = "1") page: Int,
        @RequestParam("size", required = false, defaultValue = "30") size: Int,
        @RequestParam("orderBy", required = false, defaultValue = "code") orderBy: String,
        @RequestParam("sortBy", required = false, defaultValue = "asc") sortBy: String,
        @RequestParam("groupWarehouse", required = false, defaultValue = "false") groupWarehouse: Boolean,
        @RequestParam("filter", required = false) filter: List<BasicFilter>?,
    ): StockListAllResponse?

    /**
     * - *English*: This function is used for confirm that the request of stock transfer was received
     * - *Português*: Esta função é usada para confirmar que a solicitação de transferência de estoque foi recebida
     * @param stockTransferUUID UUID
     * - *English*: This parameter will receive the identifier of request of stock transfer
     * - *Português*: Este parâmetro receberá o identificador da solicitação da requsição de transferência de estoque
     */
    fun confirmStockTransfer(stockTransferUUID: UUID)

    fun getStockTransferDetailed(requestUUID: UUID): TransferResponse

    fun listStockDetailed(
        page: Int,
        size: Int,
        orderBy: String,
        sortBy: String,
        filter: List<BasicFilter>?,
        productCode: String,
    ): StockDetailedResponse

    /**
     * - *English*: This function will turn back the last modification on request transfer
     * - *Português*: Esta função irá voltar a última modificação na solicitação de transferência
     * @param stockTransferUUID UUID
     * - *English*: This parameter will receive the identifier of request of stock transfer
     * - *Português*: Este parâmetro receberá o identificador da solicitação da requsição de transferência de estoque
     */
    fun cancelStockTransfer(
        stockTransferUUID: UUID,
    ): GenericError?

    /**
     * - *English*: This function will list the history of request stock transfer
     * - *Português*: Essa função listará o histórico da solicitação de transferência de estoque
     * @param stockTransferUUID UUID
     * - *English*: This parameter will receive the identifier of request of stock transfer
     * - *Português*: Este parâmetro receberá o identificador da solicitação da requsição de transferência de estoque
     */
    fun listStockTransferHistory(stockTransferUUID: UUID): StockTransferHistoryResponse
}