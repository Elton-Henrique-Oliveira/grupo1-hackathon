package projetoL.internationalization.enterprise.domain.usecases.implementation

import projetoL.core.shared.utils.Utils
import projetoL.core.shared.webservice.BasicFilter
import projetoL.core.shared.webservice.TotalPages
import projetoL.internationalization.enterprise.domain.entities.Enterprise
import projetoL.internationalization.enterprise.domain.repository.EnterpriseRepository
import projetoL.internationalization.enterprise.domain.usecases.EnterpriseUseCase
import projetoL.internationalization.enterprise.domain.usecases.response.EnterpriseListAllResponse
import projetoL.internationalization.enterprise.domain.usecases.response.EnterpriseResponse
import org.springframework.stereotype.Service
import java.util.*


@Service
class EnterpriseUseCaseImplementation(
    val enterpriseRepository: EnterpriseRepository,
) : EnterpriseUseCase {
    override fun createAndUpdate(enterprise: Enterprise): EnterpriseResponse? {

        if (enterprise.uuid == null) {
            return EnterpriseResponse(enterpriseRepository.create(enterprise))
        }

        return EnterpriseResponse(enterpriseRepository.update(enterprise))
    }

    override fun getAllEnterprise(
        page: Int,
        size: Int,
        orderBy: String,
        sortBy: String,
        filters: List<BasicFilter>?,
    ): EnterpriseListAllResponse? {
        return try {
            val totalPages : TotalPages? = Utils.calculateTotalPages(enterpriseRepository.getCountAllEnterprise(filters), size)

            EnterpriseListAllResponse(
                enterprise = enterpriseRepository.getAllEnterprise(page, size, orderBy, sortBy, filters),
                page = page,
                size = size,
                numberPages = totalPages!!.totalPages,
                error = null
            )
        } catch (e: Exception) {
            EnterpriseListAllResponse(error = null)
        }
    }

    override fun getEnterpriseByUUID(uuid: UUID): Enterprise? {
        return enterpriseRepository.getByUUID(uuid)
    }

}