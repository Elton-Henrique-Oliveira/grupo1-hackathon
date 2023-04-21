package singe.internationalization.called.domain.usecases

import singe.internationalization.called.domain.entities.Called
import singe.internationalization.called.domain.usecases.response.CalledResponse
import java.util.UUID

interface CalledUseCase {

    fun createAndUpdate(called: Called): CalledResponse?

    fun getCalled(): List<Called>?

    fun updateCalledSituation(calledUUID: UUID, situationUUID: UUID): Boolean?

    fun getCalledByUUID(calledUUID: UUID): Called?
}