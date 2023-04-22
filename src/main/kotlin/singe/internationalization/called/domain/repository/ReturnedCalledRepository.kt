package singe.internationalization.called.domain.repository

import singe.internationalization.called.domain.entities.ReturnedCalled
import java.util.*

interface ReturnedCalledRepository {

    fun createReturnedCalled(returnedCalled: ReturnedCalled) : ReturnedCalled?

    fun updateReturnedCalled(returnedCalled: ReturnedCalled) : ReturnedCalled?

    fun getReturnedCalled(): List<ReturnedCalled>?

    fun getReturnedCalledByDescriptionCalledUUID(calledUUID: UUID): List<ReturnedCalled>?

    fun getReturnedCalledByUUID(uuid: UUID): List<ReturnedCalled>?
}