package singe.internationalization.called.domain.usecases

import singe.internationalization.called.domain.entities.Called
import singe.internationalization.called.domain.usecases.response.CalledResponse

interface CalledUseCase {

    fun createAndUpdate(called: Called): CalledResponse?

    fun getCalled(): List<Called>?
}