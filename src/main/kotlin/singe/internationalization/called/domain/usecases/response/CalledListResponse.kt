package singe.internationalization.called.domain.usecases.response

import singe.internationalization.called.domain.entities.DescriptionCalled
import singe.internationalization.called.domain.entities.Flow
import singe.internationalization.called.domain.entities.SituationCalled
import java.time.LocalDateTime
import java.util.*

class CalledListResponse(
    var calledUUID: UUID?,
    var identifier: Long?,
    var userName: String?,
    var flow: Flow?,
    var situation: SituationCalled?,
    var branch: Long?,
    var telephone: Long?,
    var descriptionCalled: DescriptionCalled?,
    var modifiedAt: LocalDateTime?,
    var createdAt: LocalDateTime?,
) {
    override fun toString(): String {
        return "CalledListResponse=($calledUUID, $identifier, $userName, $flow, $situation, $branch, $telephone, $descriptionCalled, $modifiedAt, $createdAt)"
    }
}