package projetoL.internationalization.enterprise.infraestructure.webservice.implementation

import projetoL.core.shared.webservice.BasicFilter
import projetoL.internationalization.enterprise.domain.entities.Enterprise
import projetoL.internationalization.enterprise.domain.usecases.EnterpriseUseCase
import projetoL.internationalization.enterprise.domain.usecases.response.EnterpriseListAllResponse
import projetoL.internationalization.enterprise.domain.usecases.response.EnterpriseResponse
import projetoL.internationalization.enterprise.infraestructure.webservice.EnterpriseService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@CrossOrigin(
    origins = ["http://10.0.0.149:3000"],
    allowCredentials = "true"
)
@RequestMapping("/enterprise")
class EnterpriseServiceImplementation(
    val enterpriseUseCase: EnterpriseUseCase,
) : EnterpriseService {

    @PostMapping
    override fun createAndUpdate(enterprise: Enterprise): EnterpriseResponse? {
        return enterpriseUseCase.createAndUpdate(enterprise)
    }


    @GetMapping("")
    override fun getAllEnterprise(
        @RequestParam("page", required = false, defaultValue = "1") page: Int,
        @RequestParam("size", required = false, defaultValue = "30") size: Int,
        @RequestParam("orderBy", required = false, defaultValue = "") orderBy: String,
        @RequestParam("sortBy", required = false, defaultValue = "asc") sortBy: String,
        @RequestParam("filter", required = false) filter: List<BasicFilter>?,
    ): EnterpriseListAllResponse? {
        return enterpriseUseCase.getAllEnterprise(
            page = page,
            size = size,
            orderBy = orderBy,
            sortBy = sortBy,
            filters = filter
        )
    }

    @GetMapping("/{uuid}")
    override fun getEnterpriseByUUID(@PathVariable("uuid") uuid: UUID): Enterprise? {
        return enterpriseUseCase.getEnterpriseByUUID(uuid)
    }


}