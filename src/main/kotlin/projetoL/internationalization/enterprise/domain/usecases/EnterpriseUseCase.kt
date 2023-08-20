package projetoL.internationalization.enterprise.domain.usecases

import projetoL.core.shared.webservice.BasicFilter
import projetoL.internationalization.enterprise.domain.entities.Enterprise
import projetoL.internationalization.enterprise.domain.usecases.response.EnterpriseListAllResponse
import projetoL.internationalization.enterprise.domain.usecases.response.EnterpriseResponse
import java.util.*

interface EnterpriseUseCase {
    fun createAndUpdate(enterprise: Enterprise): EnterpriseResponse?

    fun getAllEnterprise(
        page: Int,
        size: Int,
        orderBy: String,
        sortBy: String,
        filters: List<BasicFilter>?,
    ) : EnterpriseListAllResponse?

    fun getEnterpriseByUUID(uuid: UUID) : Enterprise?
}