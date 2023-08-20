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
import projetoL.internationalization.enterprise.domain.exceptions.EMPRESA_NAO_CADASTRADA
import projetoL.internationalization.enterprise.domain.exceptions.NOME_NAO_INFORMADO
import projetoL.internationalization.enterprise.domain.exceptions.SERVIDOR_NAO_ENCONTRADO
import projetoL.internationalization.enterprise.domain.exceptions.UM_ERRO_OCORREU_NA_EMPRESA
import java.util.*


@Service
class EnterpriseUseCaseImplementation(
    val enterpriseRepository: EnterpriseRepository,
) : EnterpriseUseCase {
    override fun createAndUpdate(enterprise: Enterprise): EnterpriseResponse? {

        if (enterpriseRepository.getServerByUUID(enterprise.server!!.uuid!!) == null) {
            return EnterpriseResponse(error = SERVIDOR_NAO_ENCONTRADO)
        }

        if (enterprise.name == "" || enterprise.name == null) {
            return EnterpriseResponse(error = NOME_NAO_INFORMADO)
        }

        if (enterprise.uuid == null) {
            return EnterpriseResponse(enterpriseRepository.create(enterprise))
        }

        if(enterpriseRepository.getByUUID(enterprise.uuid!!) == null){
            return EnterpriseResponse(error = EMPRESA_NAO_CADASTRADA)
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
            val totalPages: TotalPages? =
                Utils.calculateTotalPages(enterpriseRepository.getCountAllEnterprise(filters), size)

            EnterpriseListAllResponse(
                enterprise = enterpriseRepository.getAllEnterprise(page, size, orderBy, sortBy, filters),
                page = page,
                size = size,
                numberPages = totalPages!!.totalPages,
                error = null
            )
        } catch (e: Exception) {
            EnterpriseListAllResponse(error = UM_ERRO_OCORREU_NA_EMPRESA)
        }
    }

    override fun getEnterpriseByUUID(uuid: UUID): Enterprise? {
        return enterpriseRepository.getByUUID(uuid)
    }

}