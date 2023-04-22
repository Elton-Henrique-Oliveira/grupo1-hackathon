package singe.internationalization.called.domain.usecases.implementation

import org.springframework.stereotype.Service
import singe.internationalization.called.domain.entities.Called
import singe.internationalization.called.domain.entities.ReturnFlow
import singe.internationalization.called.domain.exceptions.CALLED_CREATE_ERROR
import singe.internationalization.called.domain.exceptions.CALL_DOES_NOT_EXIST
import singe.internationalization.called.domain.repository.CalledRepository
import singe.internationalization.called.domain.repository.ReturnFlowRepository
import singe.internationalization.called.domain.usecases.response.CalledResponse
import java.util.*

@Service
class ReturnFlowUseCaseImplementation(
        val repository: ReturnFlowRepository,
) : ReturnFlowUseCase {
    override fun getReturnFlow(): List<ReturnFlow>? {
        return repository.getReturnFlow()
    }
}









