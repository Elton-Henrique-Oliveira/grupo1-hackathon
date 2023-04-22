package singe.internationalization.called.domain.usecases.implementation

import org.springframework.stereotype.Service
import singe.internationalization.called.domain.entities.ReturnedCalled
import singe.internationalization.called.domain.exceptions.RETURNED_DESCRIPTION_CALLED_CREATE_ERROR
import singe.internationalization.called.domain.exceptions.RETURNED_DESCRIPTION_CALLED_DOES_NOT_EXIST
import singe.internationalization.called.domain.repository.ReturnedCalledRepository
import singe.internationalization.called.domain.usecases.ReturnedCalledUseCase
import singe.internationalization.called.domain.usecases.response.ReturnedCalledResponse
import java.util.*

@Service
class ReturnedCalledUseCaseImplementation(
    val repository: ReturnedCalledRepository,
) : ReturnedCalledUseCase {

    override fun createAndUpdate(returnedCalled: ReturnedCalled): ReturnedCalledResponse? {
        return try {
            if (returnedCalled.uuid == null) {
                return ReturnedCalledResponse(returnedCalled = repository.createReturnedCalled(returnedCalled))
            }
            if (repository.getReturnedCalledByUUID(returnedCalled.uuid!!) == null) {
                return ReturnedCalledResponse(error = RETURNED_DESCRIPTION_CALLED_DOES_NOT_EXIST)
            }
            ReturnedCalledResponse(returnedCalled = repository.updateReturnedCalled(returnedCalled))
        } catch (e: Exception) {
            ReturnedCalledResponse(error = RETURNED_DESCRIPTION_CALLED_CREATE_ERROR)
        }
    }

    override fun getReturnedCalled(): List<ReturnedCalled>? {
        return repository.getReturnedCalled()
    }

    override fun getReturnedCalledByDescriptionCalledUUID(calledUUID: UUID): List<ReturnedCalled>? {
        return repository.getReturnedCalledByDescriptionCalledUUID(calledUUID)
    }

    override fun getReturnedCalledByUUID(uuid: UUID): List<ReturnedCalled>? {
        return repository.getReturnedCalledByUUID(uuid)
    }
}