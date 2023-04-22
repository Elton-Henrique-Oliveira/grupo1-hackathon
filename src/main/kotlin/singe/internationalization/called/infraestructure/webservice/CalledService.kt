package singe.internationalization.called.infraestructure.webservice

import br.com.lince.singe.core.shared.webservice.BasicFilter
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import singe.internationalization.called.domain.entities.Called
import singe.internationalization.called.domain.usecases.response.CalledListAllResponse
import singe.internationalization.called.domain.usecases.response.CalledResponse
import java.util.*

interface CalledService {
    fun createAndUpdate(
        @RequestBody called: Called
    ): CalledResponse?
    fun getCalled(): List<Called>?
    fun updateCalledSituation(
        @PathVariable("calledUUID") calledUUID: UUID,
        @PathVariable("situationUUID") situationUUID: UUID
    ): CalledResponse?

    fun getCalledByUUID(
        @PathVariable("calledUUID") calledUUID: UUID
    ): Called?

    fun listAllCalled(
        @RequestParam("page", required = false, defaultValue = "1") page: Int,
        @RequestParam("size", required = false, defaultValue = "30") size: Int,
        @RequestParam("orderBy", required = false, defaultValue = "") orderBy: String,
        @RequestParam("sortBy", required = false, defaultValue = "asc") sortBy: String,
        @RequestParam("filter", required = false) filter: List<BasicFilter>?,
    ): CalledListAllResponse
}