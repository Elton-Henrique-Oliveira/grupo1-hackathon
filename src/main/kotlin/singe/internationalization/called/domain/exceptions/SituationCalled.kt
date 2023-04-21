package singe.internationalization.called.domain.exceptions

import br.com.lince.singe.core.shared.error.GenericError

val SITUATION_CREATE_ERROR =
        SituationCalledException("SITUATION_CREATE_ERROR", "An error has ocurred on create situation")


class SituationCalledException(
        code: String,
        description: String
) : GenericError("situationCalled-module", code, description)