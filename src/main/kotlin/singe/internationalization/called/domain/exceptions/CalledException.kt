package singe.internationalization.called.domain.exceptions

import br.com.lince.singe.core.shared.error.GenericError

val CALLED_CREATE_ERROR =
    CalledException("CALLED_CREATE_ERROR", "An error has ocurred on create called")


class CalledException(
    code: String,
    description: String
) : GenericError("called-module", code, description)