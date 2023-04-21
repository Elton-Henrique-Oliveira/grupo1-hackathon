package singe.internationalization.called.domain.usecases.implementation

import org.springframework.stereotype.Service
import singe.internationalization.called.domain.entities.FlowType
import singe.internationalization.called.domain.repository.FlowTypeRepository
import singe.internationalization.called.domain.usecases.FlowTypeUseCase

@Service
class FlowTypeUseCaseImplementation(
    val repository: FlowTypeRepository,
) : FlowTypeUseCase {
    override fun getFlowType(): List<FlowType>? {
        return repository.getFlowType()
    }
}