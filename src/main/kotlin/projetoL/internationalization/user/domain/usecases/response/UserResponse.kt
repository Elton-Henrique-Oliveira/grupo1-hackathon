package projetoL.internationalization.user.domain.usecases.response

import projetoL.internationalization.user.domain.entities.User
import projetoL.internationalization.user.domain.exceptions.UserException

data class UserResponse (
    var user: User? = null,
    var error: UserException?= null
)