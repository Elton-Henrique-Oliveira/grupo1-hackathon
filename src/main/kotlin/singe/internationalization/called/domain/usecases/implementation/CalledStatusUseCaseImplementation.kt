package singe.internationalization.called.domain.usecases.implementation

import org.springframework.stereotype.Service
import singe.internationalization.called.domain.entities.CalledStatus
import singe.internationalization.called.domain.repository.CalledStatusRepository
import singe.internationalization.called.domain.usecases.CalledStatusUseCase

@Service
class CalledStatusUseCaseImplementation (
    val repository: CalledStatusRepository,
    ) : CalledStatusUseCase {
        override fun getCalledStatus(): List<CalledStatus>? {
            return repository.getCalledStatus()
        }
}