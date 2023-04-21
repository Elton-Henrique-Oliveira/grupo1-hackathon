import br.com.lince.singe.core.shared.error.GenericError

val STOCK_LIST_ERROR =
    StockException("STOCK_LIST_ERROR", "An error has ocurred on stock list")
val STOCK_TRANSFER_EMPTY_BOXES = StockException("STOCK_TRANSFER_EMPTY_BOXES", "Chose some box")
val STOCK_TRANSFER_EMPTY_ORIGIN_WAREHOUSE =
    StockException("STOCK_TRANSFER_EMPTY_ORIGIN_WAREHOUSE", "Chose some origin warehouse")
val STOCK_TRANSFER_EMPTY_DESTINY_WAREHOUSE =
    StockException("STOCK_TRANSFER_EMPTY_DESTINY_WAREHOUSE", "Chose some destiny warehouse")
val STOCK_TRANSFER_ORIGIN_WAREHOUSE_NOT_FOUND =
    StockException("STOCK_TRANSFER_ORIGIN_WAREHOUSE_NOT_FOUND", "Origin warehouse not found")
val STOCK_TRANSFER_DESTINY_WAREHOUSE_NOT_FOUND =
    StockException("STOCK_TRANSFER_DESTINY_WAREHOUSE_NOT_FOUND", "Destiny warehouse not found")
val STOCK_TRANSFER_BOX_NOT_FOUND =
    StockException("STOCK_TRANSFER_BOX_NOT_FOUND", "Box not found")
val STOCK_TRANSFER_STORAGE_ERROR =
    StockException("STOCK_TRANSFER_DATABASE_ERROR", "An error has ocurred on stock storage")
val WAREHOUSE_IS_NOT_SEPARATION =
    StockException("WAREHOUSE_IS_NOT_SEPARATION", "Warehouse needs be separation to be destiny")
val BOX_ALREADY_REQUESTED =
    StockException("BOX_ALREADY_REQUESTED", "The box has already been ordered")
val WAREHOUSE_IS_SEPARATION =
    StockException("WAREHOUSE_IS_SEPARATION", "Warehouse is only separation, can not be origin")
val STOCK_TRANSFER_REQUEST_NOT_FOUND =
    StockException("STOCK_TRANSFER_REQUEST_NOT_FOUND", "Request transfer not found")
val INVALID_BOX_WAREHOUSE =
    StockException("INVALID_BOX_WAREHOUSE", "The box isn't in origin warehouse")
val ERROR_SENDING_EMAIL =
    StockException("ERROR_SENDING_EMAIL", "The email could not be sent")
val CANT_TRANSFER_TO_ITSELF =
    StockException("CANT_TRANSFER_TO_ITSELF", "Can't transfer boxes to the same warehouse it's already in")

class StockException(
    code: String,
    description: String
) : GenericError("stock-module", code, description)