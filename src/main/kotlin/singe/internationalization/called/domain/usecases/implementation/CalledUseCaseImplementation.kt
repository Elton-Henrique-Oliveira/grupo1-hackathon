package singe.internationalization.called.domain.usecases.implementation

import org.springframework.stereotype.Service
import singe.internationalization.called.domain.entities.Called
import singe.internationalization.called.domain.exceptions.*
import singe.internationalization.called.domain.repository.CalledRepository
import singe.internationalization.called.domain.usecases.CalledUseCase
import singe.internationalization.called.domain.usecases.response.CalledResponse
import java.util.*

@Service
class CalledUseCaseImplementation(
    val repository: CalledRepository,
) : CalledUseCase {
    override fun createAndUpdate(called: Called): CalledResponse {
        return try {
            println(called.uuid)
            println(called.identifier)
            println(called.uuid)
            println(called.uuid)

            if (called.uuid == null) {
                if (called.identifier.isNullOrBlank()) {
                    return CalledResponse(error = IDENTIFIER_NOT_INFORMED)
                }
                if(repository.getCalledByIdentifier(called.identifier!!) != null){
                    return CalledResponse(error = IDENTIFIER_ALREADY_REGISTERED)
                }
                return CalledResponse(called = repository.createCalled(called))
            }
            println("veio aqui porra")
            if (called.identifier.isNullOrBlank()) {
                return CalledResponse(error = IDENTIFIER_NOT_INFORMED)
            }
            if(repository.getCalledByUUID(called.uuid!!) == null){
                return CalledResponse(error = CALL_DOES_NOT_EXIST)
            }
            CalledResponse(called = repository.updateCalled(called))
        } catch (e: Exception) {
            println(e)
            CalledResponse(error = CALLED_CREATE_ERROR)
        }
    }

    override fun getCalled(): List<Called>? {
        return repository.getCalled()
    }

    override fun updateCalledSituation(calledUUID: UUID, situationUUID: Int): Boolean? {
        if (repository.getCalledByUUID(calledUUID) == null) {
            return false
        }

        if (situationUUID == 0) {
            return false
        }

        return repository.updateCalledSituation(calledUUID, situationUUID)
    }

    override fun getCalledByUUID(calledUUID: UUID): Called? {
        return repository.getCalledByUUID(calledUUID)
    }
}
