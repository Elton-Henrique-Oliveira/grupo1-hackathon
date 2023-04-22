package singe.internationalization.called.domain.usecases.implementation

import org.springframework.stereotype.Service
import singe.internationalization.called.domain.entities.Priority
import singe.internationalization.called.domain.repository.PriorityRepository
import singe.internationalization.called.domain.usecases.PriorityUseCase

@Service
class PriorityUseCaseImplementation(
    var repository: PriorityRepository
) : PriorityUseCase{
    override fun getPriority(): List<Priority>? {
        return repository.getPriority()
    }
}