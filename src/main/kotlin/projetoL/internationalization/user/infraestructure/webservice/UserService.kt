package projetoL.internationalization.user.infraestructure.webservice

import org.springframework.web.bind.annotation.PathVariable
import projetoL.core.shared.webservice.BasicFilter
import projetoL.internationalization.user.domain.entities.User
import projetoL.internationalization.user.domain.usecases.response.UserListAllResponse
import projetoL.internationalization.user.domain.usecases.response.UserResponse
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import projetoL.internationalization.user.domain.entities.LoginRequest
import projetoL.internationalization.user.domain.entities.PasswordChangeRequest
import java.util.UUID

interface UserService {

    fun createAndUpdate(
        user: User
    ): UserResponse?

    fun getAllUser(
        page: Int,
        size: Int,
        orderBy: String,
        sortBy: String,
        filters: List<BasicFilter>?,
        enterpriseUUID: UUID
    ): UserListAllResponse?

    fun getUserByUUID(
        uuid: UUID,
        enterpriseUUID: UUID
    ): User?

    fun requestLogin(
        loginRequest: LoginRequest
    ): UserResponse?

    fun passwordChangeRequest(
        passwordChangeRequest: PasswordChangeRequest
    ): UserResponse?
}