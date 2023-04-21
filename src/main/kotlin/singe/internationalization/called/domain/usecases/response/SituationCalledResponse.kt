package singe.internationalization.called.domain.usecases.response

import singe.internationalization.called.domain.entities.SituationCalled
import singe.internationalization.called.domain.exceptions.SituationCalledException

data class SituationCalledResponse(
        var called: SituationCalled? = null,
        var error: SituationCalledException? = null
)

