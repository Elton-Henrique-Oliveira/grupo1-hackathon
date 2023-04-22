package singe.internationalization.called.domain.usecases

import singe.internationalization.called.domain.entities.CalledStatus
import java.util.*

interface CalledStatusUseCase {
    fun getCalledStatus(): List<CalledStatus>?
}





