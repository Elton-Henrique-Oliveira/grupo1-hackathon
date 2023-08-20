package projetoL.internationalization.enterprise.infraestructure.webservice

import projetoL.core.shared.webservice.BasicFilter
import projetoL.internationalization.enterprise.domain.entities.Enterprise
import projetoL.internationalization.enterprise.domain.usecases.response.EnterpriseListAllResponse
import projetoL.internationalization.enterprise.domain.usecases.response.EnterpriseResponse
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

interface EnterpriseService {

    fun createAndUpdate(
        @RequestBody enterprise: Enterprise
    ): EnterpriseResponse?

    fun getAllEnterprise(
        @RequestParam("page", required = false, defaultValue = "1") page: Int,
        @RequestParam("size", required = false, defaultValue = "30") size: Int,
        @RequestParam("orderBy", required = false, defaultValue = "") orderBy: String,
        @RequestParam("sortBy", required = false, defaultValue = "asc") sortBy: String,
        @RequestParam("filter", required = false) filter: List<BasicFilter>?,
    ): EnterpriseListAllResponse?

    fun getEnterpriseByUUID(
        @PathVariable("uuid") uuid: UUID
    ): Enterprise?
}