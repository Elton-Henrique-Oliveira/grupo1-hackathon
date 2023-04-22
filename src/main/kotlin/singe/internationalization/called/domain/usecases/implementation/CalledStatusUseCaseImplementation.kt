package singe.internationalization.called.domain.usecases.implementation

import singe.internationalization.called.domain.entities.CalledStatus
import singe.internationalization.called.domain.repository.CalledStatusRepository
import singe.internationalization.called.domain.usecases.CalledStatusUseCase
import java.util.*

class CalledStatusUseCaseImplementation (
    val repository: CalledStatusRepository,
    ) : CalledStatusUseCase {
        override fun getCalledStatus(): List<CalledStatus>? {
            return repository.getCalledStatus()
        }
}