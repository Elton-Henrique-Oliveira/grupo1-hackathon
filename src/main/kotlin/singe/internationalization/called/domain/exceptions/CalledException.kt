package singe.internationalization.called.domain.exceptions

import br.com.lince.singe.core.shared.error.GenericError

val CALLED_ERROR =
    CalledException("CALLED_ERROR", "An error has ocurred on called")


val CALLED_CREATE_ERROR =
    CalledException("CALLED_CREATE_ERROR", "An error has ocurred on create called")

val UNREGISTERED_IDENTIFIER =
    CalledException("UNREGISTERED IDENTIFIER", "an error because the identifier was not informed")

val IDENTIFIER_NOT_INFORMED =
    CalledException("IDENTIFIER NOT INFORMED", "an error because the identifier was not informed")


val IDENTIFIER_ALREADY_REGISTERED =
    CalledException("IDENTIFIER ALREADY REGISTERED", "an error occurred because this identifier already exists in the system")

val CALL_DOES_NOT_EXIST =
    CalledException("CALL DOES NOT EXIST","an error occurred because the ticket does not exist in the system")

val SITUATION_DOES_NOT_EXIST =
    CalledException("SITUATION DOES NOT EXIST","an error occurred because the situation does not exist in the system")
class CalledException(
    code: String,
    description: String
) : GenericError("called-module", code, description)