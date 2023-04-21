package br.com.lince.singe.internationalization.stock.domain.usecases.response

data class StockFilter(
    val name: String,
    val value: String,
) {
    override fun toString(): String {
        return "StockFilter=($name, $value)"
    }
}