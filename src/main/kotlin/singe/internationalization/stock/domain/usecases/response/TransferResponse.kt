package br.com.lince.singe.internationalization.stock.domain.usecases.response

import br.com.lince.singe.core.shared.error.GenericError

/**
 * - *English*: Simple response for the stock transfer
 * - *Português*: Simples resposta para a transferência de estoque
 * @param stockTransfer: stockTransfer
 * - *English*: The data tha was request the transfer
 * - *Português*: Os dados solicitados para a transferência
 * @param error: GenericError
 * - *English*: A description of the error that stopped the stock transfer
 * - *Português*: Uma descrição do erro que parou a transferência de estoque
 */
data class TransferResponse(
    val error: GenericError? = null,
)