import br.com.lince.singe.internationalization.stock.domain.entities.Stock

data class StockResponse(
    val stock: Stock? = null,
    val error: StockException? = null
)