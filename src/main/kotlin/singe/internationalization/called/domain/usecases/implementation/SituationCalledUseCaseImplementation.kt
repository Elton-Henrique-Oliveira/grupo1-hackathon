package singe.internationalization.called.domain.usecases.implementation

import org.springframework.stereotype.Service
import singe.internationalization.called.domain.entities.SituationCalled
import singe.internationalization.called.domain.repository.SituationCalledRepository
import singe.internationalization.called.domain.usecases.SituationCalledUseCase


@Service
class SituationCalledUseCaseImplementation(
        val repository: SituationCalledRepository,
) : SituationCalledUseCase {
    override fun getSituationCalled(): List<SituationCalled>? {
        return repository.getSituationCalled()
    }

}
