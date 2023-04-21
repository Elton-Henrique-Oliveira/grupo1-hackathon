package singe.internationalization.called.domain.exceptions

import br.com.lince.singe.core.shared.error.GenericError

val CALLEDTYPE_CREATE_ERROR =
        CalledTypeException("CALLEDTYPE_CREATE_ERROR", "An error has ocurred on create called type")


class CalledTypeException(
        code: String,
        description: String
) : GenericError("calledType-module", code, description)