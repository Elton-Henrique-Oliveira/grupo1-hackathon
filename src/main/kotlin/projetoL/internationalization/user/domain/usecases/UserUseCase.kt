package projetoL.internationalization.user.domain.usecases

import projetoL.core.shared.webservice.BasicFilter
import projetoL.internationalization.user.domain.entities.LoginRequest
import projetoL.internationalization.user.domain.entities.PasswordChangeRequest
import projetoL.internationalization.user.domain.entities.User
import projetoL.internationalization.user.domain.usecases.response.UserListAllResponse
import projetoL.internationalization.user.domain.usecases.response.UserResponse
import java.util.*

interface UserUseCase {

    fun createAndUpdate(user: User) : UserResponse?

    fun getAllUser(
        page: Int,
        size: Int,
        orderBy: String,
        sortBy: String,
        filters: List<BasicFilter>?,
        enterpriseUUID: UUID
    ) : UserListAllResponse?

    fun getUserByUUID(uuid: UUID, enterpriseUUID: UUID) : User?

    fun requestLogin(loginRequest: LoginRequest) : UserResponse?

    fun passwordChangeRequest(passwordChangeRequest: PasswordChangeRequest) : UserResponse?
}