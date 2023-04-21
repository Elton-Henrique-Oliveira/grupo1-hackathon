package singe.internationalization.called.domain.repository


import singe.internationalization.called.domain.entities.SituationCalled
import java.util.UUID

interface SituationCalledRepository {
    fun getSituationCalled(): List<SituationCalled>

    fun getSituationCalledByUUID(situationUUID: UUID): SituationCalled?

}
