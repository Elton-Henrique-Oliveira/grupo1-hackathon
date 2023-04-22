package singe.internationalization.called.domain.usecases

import org.springframework.stereotype.Service
import singe.internationalization.called.domain.entities.ReturnedCalled
import singe.internationalization.called.domain.usecases.response.ReturnedCalledResponse
import java.util.*

interface ReturnedCalledUseCase {
    fun createAndUpdate(returnedCalled: ReturnedCalled): ReturnedCalledResponse?

    fun getReturnedCalled(): List<ReturnedCalled>?

    fun getReturnedCalledByDescriptionCalledUUID(calledUUID: UUID): List<ReturnedCalled>?

    fun getReturnedCalledByUUID(uuid: UUID): List<ReturnedCalled>?
}