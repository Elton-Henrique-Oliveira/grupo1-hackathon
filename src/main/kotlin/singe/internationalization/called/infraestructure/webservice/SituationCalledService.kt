package singe.internationalization.called.infraestructure.webservice



import singe.internationalization.called.domain.entities.SituationCalled

interface SituationCalledService {
    fun getSituationCalled(): List<SituationCalled>?
}
