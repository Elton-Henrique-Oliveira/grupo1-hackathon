package singe.internationalization.called.infraestructure.webservice

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import singe.internationalization.called.domain.entities.Called
import singe.internationalization.called.domain.usecases.response.CalledResponse
import java.util.*

interface CalledService {
    fun createAndUpdate(
        @RequestBody called: Called
    ): CalledResponse?
    fun getCalled(): List<Called>?
    fun updateCalledSituation(
        @PathVariable("calledUUID") calledUUID: UUID,
        @PathVariable("situationUUID") situationUUID: Int
    ): Boolean?

    fun getCalledByUUID(
        @PathVariable("calledUUID") calledUUID: UUID
    ): Called?
}