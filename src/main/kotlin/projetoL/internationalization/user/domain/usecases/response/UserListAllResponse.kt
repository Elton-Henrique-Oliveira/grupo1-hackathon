package projetoL.internationalization.user.domain.usecases.response

import projetoL.core.shared.error.GenericError
import projetoL.internationalization.user.domain.entities.User

class UserListAllResponse(
    val user: List<User>? = null,
    val page: Int? = null,
    val size: Int? = null,
    val numberPages: Int? = null,
    var error: GenericError? = null
) {
    override fun toString(): String {
        return "UserListAllResponse=($user, $page, $size , $numberPages, $error)"
    }
}