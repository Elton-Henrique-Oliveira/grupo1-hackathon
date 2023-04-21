package singe.internationalization.called.domain.usecases.implementation

import org.springframework.stereotype.Service
import singe.internationalization.called.domain.entities.Called
import singe.internationalization.called.domain.exceptions.CALLED_CREATE_ERROR
import singe.internationalization.called.domain.repository.CalledRepository
import singe.internationalization.called.domain.usecases.CalledUseCase
import singe.internationalization.called.domain.usecases.response.CalledResponse

@Service
class CalledUseCaseImplementation(
    val repository: CalledRepository,
) : CalledUseCase {
    override fun createAndUpdate(called: Called): CalledResponse {
        return try {
            if (called.uuid == null) {
                if (called.identifier == "") {
                    return CalledResponse(error = CALLED_CREATE_ERROR)
                }
                return CalledResponse(called = repository.createCalled(called))
            }
            if (called.identifier.isNullOrBlank()) {
                return CalledResponse(error = CALLED_CREATE_ERROR)
            }
            //CalledResponse(called = repository.updateCalled(called))
            CalledResponse(error = CALLED_CREATE_ERROR)
        } catch (e: Exception) {
            CalledResponse(error = CALLED_CREATE_ERROR)
        }
    }

    override fun getCalled(): List<Called>? {
        return repository.getCalled()
    }
}

