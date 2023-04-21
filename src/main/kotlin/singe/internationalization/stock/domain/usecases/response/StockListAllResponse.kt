package br.com.lince.singe.internationalization.stock.domain.usecases.response

import br.com.lince.singe.core.shared.error.GenericError

data class StockListAllResponse(
    val stock: List<StockListResponse>? = null,
    val page: Int? = null,
    val size: Int? = null,
    val numberPages: Int?= null,
    var error: GenericError? = null
){
    override fun toString(): String {
        return "StockListAllResponse=($stock, $page, $size , $numberPages, $error)"
    }
}