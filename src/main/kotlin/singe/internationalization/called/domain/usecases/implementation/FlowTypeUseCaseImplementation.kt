package singe.internationalization.called.domain.usecases.implementation

import org.springframework.stereotype.Service
import singe.internationalization.called.domain.entities.FlowType
import singe.internationalization.called.domain.repository.FlowTypeRepository
import singe.internationalization.called.domain.usecases.FlowTypeUseCase
import java.util.*

@Service
class FlowTypeUseCaseImplementation(
    val repository: FlowTypeRepository,
) : FlowTypeUseCase {
    override fun getFlowType(): List<FlowType>? {
        return repository.getFlowType()
    }

    override fun getFlowTypeByFlowUUID(flowUUID: UUID): List<FlowType>? {
        return repository.getFlowTypeByFlowUUID(flowUUID)
    }
}