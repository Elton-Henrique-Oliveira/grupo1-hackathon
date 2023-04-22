package singe.internationalization.called.infraestructure.webservice

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import singe.internationalization.called.domain.entities.ReturnedCalled
import singe.internationalization.called.domain.usecases.response.ReturnedCalledResponse
import java.util.*

interface ReturnedCalledService {

    fun createAndUpdate(
        @RequestBody returnedCalled: ReturnedCalled
    ): ReturnedCalledResponse?

    fun getReturnedCalled(): List<ReturnedCalled>?

    fun getReturnedCalledByUUID(
        @PathVariable("uuid") uuid: UUID
    ): List<ReturnedCalled>?
}