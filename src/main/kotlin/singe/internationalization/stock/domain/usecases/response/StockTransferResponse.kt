package br.com.lince.singe.internationalization.stock.domain.usecases.response

import br.com.lince.singe.core.shared.error.GenericError

data class StockTransferResponse(
    val page: Int? = null,
    val size: Int? = null,
    val numberPages: Int? = null,
    val error: GenericError? = null,
)