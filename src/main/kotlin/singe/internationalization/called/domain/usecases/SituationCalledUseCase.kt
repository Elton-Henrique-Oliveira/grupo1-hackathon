package singe.internationalization.called.domain.usecases

import singe.internationalization.called.domain.entities.SituationCalled

interface SituationCalledUseCase {
    fun getSituationCalled(): List<SituationCalled>?
}
