package singe.internationalization.called.domain.repository


import singe.internationalization.called.domain.entities.SituationCalled

interface SituationCalledRepository {
    fun getSituationCalled(): List<SituationCalled>

}
