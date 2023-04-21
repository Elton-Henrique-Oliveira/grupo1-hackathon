package singe.internationalization.called.domain.repository

import singe.internationalization.called.domain.entities.Called
import java.util.UUID

interface CalledRepository{

    fun createCalled(called: Called): Called

    fun updateCalled(called: Called): Called

    fun getCalled(): List<Called>

    fun updateCalledSituation(calledUUID: UUID, situationUUID: UUID): Boolean?

    fun getCalledByUUID(calledUUID: UUID): Called?

    fun getCalledByIdentifier(calledIdentifier: Long): Called?
}