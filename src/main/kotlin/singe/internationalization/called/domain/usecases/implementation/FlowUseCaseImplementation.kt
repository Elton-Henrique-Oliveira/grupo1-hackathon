package singe.internationalization.called.domain.usecases.implementation


import org.springframework.stereotype.Service
import singe.internationalization.called.domain.entities.Flow
import singe.internationalization.called.domain.repository.FlowRepository
import singe.internationalization.called.domain.usecases.FlowUseCase


@Service
class FlowUseCaseImplementation(
        val repository: FlowRepository,
) : FlowUseCase {
    override fun getFlow(): List<Flow>? {
        return repository.getFlow()
    }

}


