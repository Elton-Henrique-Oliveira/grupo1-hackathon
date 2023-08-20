package projetoL.internationalization.user.domain.repository

import projetoL.core.shared.webservice.BasicFilter
import projetoL.internationalization.user.domain.entities.LoginRequest
import projetoL.internationalization.user.domain.entities.PasswordChangeRequest
import projetoL.internationalization.user.domain.entities.User
import projetoL.internationalization.user.domain.entities.UserType
import java.util.UUID

interface UserRepository {

    fun create(user: User) : User

    fun update(user: User) : User

    fun updatePassword(passwordChangeRequest: PasswordChangeRequest)

    fun getByUUID(uuid: UUID) : User?

    fun getByLoginRequest(loginRequest: LoginRequest) : User?

    fun getAllUser(
        page: Int,
        size: Int,
        orderBy: String,
        sortBy: String,
        filters: List<BasicFilter>?
    ) : List<User>?

    fun getCountAllUser(
        filters: List<BasicFilter>?
    ) : Int

    fun getTypeByUUID(uuid: UUID) : UserType?
}