package projetoL.internationalization.enterprise.domain.usecases.response

import projetoL.internationalization.enterprise.domain.entities.Enterprise
import projetoL.internationalization.enterprise.domain.exceptions.EnterpriseException

data class EnterpriseResponse(
    var enterprise: Enterprise? = null,
    var error: EnterpriseException? = null
)