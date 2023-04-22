package singe.internationalization.called.domain.repository

import br.com.lince.singe.core.shared.webservice.BasicFilter
import singe.internationalization.called.domain.entities.Called
import singe.internationalization.called.domain.usecases.response.CalledListResponse
import java.util.UUID

interface CalledRepository{

    fun createCalled(called: Called): Called

    fun updateCalled(called: Called): Called

    fun getCalled(): List<Called>

    fun updateCalledSituation(calledUUID: UUID, situationUUID: UUID): Called?

    fun getCalledByUUID(calledUUID: UUID): Called?

    fun getCalledByIdentifier(calledIdentifier: Long): Called?

    fun listAllCalled(
        page: Int,
        size: Int,
        orderBy: String,
        sortBy: String,
        filters: List<BasicFilter>?
    ): List<CalledListResponse>

    fun getCountAllCalled(filters: List<BasicFilter>?): Int
}