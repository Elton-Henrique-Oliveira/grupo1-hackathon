package projetoL.internationalization.user.domain.exceptions

import projetoL.core.shared.error.GenericError

val USER_DOES_NOT_EXIST =
    UserException(
        "USER_DOES_NOT_EXIST",
        "an error occurred because the user does not exist in the system"
    )

class UserException(
    code: String,
    description: String
) : GenericError("user-module", code, description)