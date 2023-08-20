package projetoL.internationalization.enterprise.domain.usecases.response

import projetoL.core.shared.error.GenericError
import projetoL.internationalization.enterprise.domain.entities.Enterprise

class EnterpriseListAllResponse(
    val enterprise: List<Enterprise>? = null,
    val page: Int? = null,
    val size: Int? = null,
    val numberPages: Int? = null,
    var error: GenericError? = null
) {
    override fun toString(): String {
        return "EnterpriseListAllResponse=($enterprise, $page, $size , $numberPages, $error)"
    }
}